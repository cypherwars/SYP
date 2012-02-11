package com.narphorium.freebase.services;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;

import com.narphorium.freebase.results.ReconciliationResult;
import com.narphorium.freebase.results.ReconciliationResultSet;

public class ReconciliationService extends AbstractFreebaseService {

	private static final Log LOG = LogFactory
			.getLog(ReconciliationService.class);

	public ReconciliationService(final HttpClient httpClient) {
		super(httpClient);
	}

	public ReconciliationService(final URL baseUrl, final HttpClient httpClient) {
		super(baseUrl, httpClient);
	}

	public final ReconciliationResultSet reconcile(
			final Map<String, Object> values) {
		try {
			final URL url = new URL(getBaseUrl() + "/reconciliation/query");
			LOG.debug("URL: " + url);

			final String query = buildQuery(values);
			LOG.debug("Query: " + query);

			final Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("q", query);

			final String response = postContent(url, parameters);
			LOG.debug("Response: " + response);

			return new ReconciliationResultSet(parseResults(response));
		} catch (final IOException e) {
			LOG.error(e.getMessage(), e);
		}

		return new ReconciliationResultSet();
	}

	private String buildQuery(final Map<String, Object> values) {
		return generateJSON(values);
	}

	@SuppressWarnings("unchecked")
	private List<ReconciliationResult> parseResults(final String response) {
		final List<ReconciliationResult> results = new ArrayList<ReconciliationResult>();
		try {
			final List<Map<String, Object>> data = (List<Map<String, Object>>) parseJSON(response);
			for (final Map<String, Object> entry : data) {
				final String id = entry.get("id").toString();
				final List<String> names = (List<String>) entry.get("name");
				final List<String> types = (List<String>) entry.get("type");
				final Double score = Double.parseDouble(entry.get("score")
						.toString());
				final boolean match = Boolean.parseBoolean(entry.get("match")
						.toString());
				results.add(new ReconciliationResult(id, names, types, score,
						match));
			}
		} catch (final IOException e) {
			LOG.error(e.getMessage(), e);
		}

		return results;
	}
}
