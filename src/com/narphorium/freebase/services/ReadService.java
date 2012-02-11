package com.narphorium.freebase.services;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;

import com.narphorium.freebase.query.Query;
import com.narphorium.freebase.results.ResultSet;
import com.narphorium.freebase.services.exceptions.FreebaseServiceException;
import com.narphorium.freebase.services.exceptions.FreebaseServiceTimeoutException;

public class ReadService extends AbstractFreebaseService {

	public ReadService(final HttpClient httpClient) {
		super(httpClient);
	}

	public ReadService(final URL baseUrl, final HttpClient httpClient) {
		super(baseUrl, httpClient);
	}

	@SuppressWarnings("unchecked")
	public final Map<String, Object> readRaw(final Query query,
			final Object cursor) throws IOException, FreebaseServiceException {
		final List<Query> queries = new ArrayList<Query>();
		queries.add(query);

		final List<Object> cursors = new ArrayList<Object>();
		cursors.add(cursor);

		final String envelope = buildReadQueryEnvelope(queries, cursors);
		final String url = getBaseUrl() + "/service/mqlread?queries="
				+ URLEncoder.encode(envelope, "UTF-8");

		final String response = fetchPage(url);
		final Map<String, Object> data = (Map<String, Object>) parseJSON(response);
		final Map<String, Object> result = (Map<String, Object>) data.get(query
				.getName());
		parseServiceErrors(query, result);
		return result;
	}

	public final Map<String, Object> readRaw(final Query query)
			throws IOException, FreebaseServiceException {
		return readRaw(query, true);
	}

	public final ResultSet read(final Query query) throws IOException {
		return read(query, null);
	}

	public final ResultSet read(final Query query, final String cursor)
			throws IOException {
		return query.buildResultSet(this);
	}

	protected final String buildReadQueryEnvelope(final List<Query> queries,
			final List<Object> cursors) {
		String envelope = "{";
		final Iterator<Query> i = queries.iterator();
		final Iterator<Object> j = cursors.iterator();
		while (i.hasNext() && j.hasNext()) {
			final Query query = i.next();
			final Object cursor = j.next();
			envelope += "\"" + query.getName() + "\":{";
			envelope += "\"query\":" + query.toJSON();
			envelope += ",\"cursor\":";
			if (cursor instanceof Boolean) {
				envelope += cursor.toString();
			} else {
				envelope += "\"" + cursor + "\"";
			}

			envelope += "}";
			if (i.hasNext()) {
				envelope += ",";
			}
		}

		envelope += "}";
		return envelope;
	}

	@SuppressWarnings("unchecked")
	public final void parseServiceErrors(final Query query,
			final Map<String, Object> data) throws FreebaseServiceException {
		// Map<String, Object> responseData = (Map<String, Object>)response;
		// Map<String, Object> queryData = responseData; //(Map<String,
		// Object>)responseData.get(query.getName());
		final String responseCode = data.get("code").toString();
		if (responseCode.equals("/api/status/error")) {
			final List<Map<String, Object>> messages = (List<Map<String, Object>>) data
					.get("messages");
			final Map<String, Object> message = messages.get(0);
			final String code = message.get("code").toString();
			final String description = message.get("message").toString();
			// final Map<String, Object> info = (Map<String, Object>)
			// message.get("info");
			final String host = null; // info.get("host").toString();
			final int port = 0; // Integer.parseInt(info.get("port").toString());
			final double timeout = 0; // Double.parseDouble(info.get("timeout").toString());
			if (code.equals(FreebaseServiceTimeoutException.ERRORCODE)) {
				throw new FreebaseServiceTimeoutException(description, host,
						port, timeout);
			} else {
				throw new FreebaseServiceException(code, description, host,
						port, timeout);
			}
		}
	}

}
