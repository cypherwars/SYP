package com.narphorium.freebase.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;

import com.narphorium.freebase.query.Query;
import com.narphorium.freebase.results.AbstractResultSet;
import com.narphorium.freebase.services.exceptions.FreebaseServiceException;

public class WriteService extends AbstractFreebaseService {

	private static final Log LOG = LogFactory.getLog(AbstractResultSet.class);

	public WriteService(final HttpClient httpClient) {
		super(httpClient);
	}

	public WriteService(final URL baseUrl, final HttpClient httpClient) {
		super(baseUrl, httpClient);
	}

	public final boolean authenticate(final String username,
			final String password) throws FreebaseServiceException {
		try {
			final URL url = new URL(getBaseUrl() + "/account/login");
			final Map<String, String> content = new HashMap<String, String>();
			content.put("username", username);
			content.put("password", password);
			final String result = postContent(url, content);
			return null != result && !result.isEmpty();
		} catch (final MalformedURLException e) {
			LOG.error(e.getMessage(), e);
		}

		return false;
	}

	public final boolean logout() {
		try {
			final URL url = new URL(getBaseUrl() + "/account/logout");
			final String result = postContent(url, new HashMap<String, String>(
					0));
			return null != result && !result.isEmpty();
		} catch (final MalformedURLException e) {
			LOG.error(e.getMessage(), e);
		}

		return false;
	}

	public final String write(final Query query)
			throws FreebaseServiceException {
		final List<Query> queries = new ArrayList<Query>();
		queries.add(query);
		return write(queries);
	}

	public final String write(final List<Query> queries)
			throws FreebaseServiceException {
		try {
			final URL url = new URL(getBaseUrl() + "/service/mqlwrite");
			final String envelope = buildWriteQueryEnvelope(queries);
			final Map<String, String> content = new HashMap<String, String>();
			content.put("queries", envelope);
			return postContent(url, content);
		} catch (final MalformedURLException e) {
			LOG.error(e.getMessage(), e);
		}

		return null;
	}

	protected final String buildWriteQueryEnvelope(final List<Query> queries) {
		String envelope = "{";
		final Iterator<Query> i = queries.iterator();
		while (i.hasNext()) {
			final Query query = i.next();
			envelope += "\"" + query.getName() + "\":{";
			envelope += "\"query\":" + query.toJSON();
			envelope += "}";
			if (i.hasNext()) {
				envelope += ",";
			}
		}

		envelope += "}";
		return envelope;
	}
}
