package com.narphorium.freebase.results;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.narphorium.freebase.query.DefaultQuery;
import com.narphorium.freebase.query.Query;
import com.narphorium.freebase.services.ReadService;
import com.narphorium.freebase.services.exceptions.FreebaseServiceException;

public abstract class AbstractResultSet implements ResultSet {

	private static final Log LOG = LogFactory.getLog(AbstractResultSet.class);

	private ReadService readService;
	private Query query;
	private List<Result> results = new ArrayList<Result>();
	private int currentResult;
	private Object cursor;
	private int numPages;
	private boolean fetchedFirstPage;

	public AbstractResultSet(final Query query, final ReadService readService) {
		this.readService = readService;
		this.query = new DefaultQuery(query);
		reset();
	}

	public final Query getQuery() {
		return query;
	}

	public final int getNumpages() {
		return numPages;
	}

	public Result current() {
		return currentResult >= 0 && currentResult < results.size() ? results
				.get(currentResult) : null;
	}

	public final void reset() {
		fetchedFirstPage = false;
		currentResult = -1;
		cursor = true;
	}

	public final int size() throws FreebaseServiceException {
		if (!fetchedFirstPage) {
			fetchNextPage();
		}

		return results.size();
	}

	public final boolean isEmpty() {
		return results.isEmpty();
	}

	public final Result next() throws FreebaseServiceException {
		++currentResult;
		if (currentResult >= (results.size() - 1)
				&& ((cursor instanceof Boolean && (Boolean) cursor == true) || (cursor instanceof String))) {
			fetchNextPage();
		}

		return current();
	}

	public final boolean hasNext() throws FreebaseServiceException {
		if (!fetchedFirstPage) {
			fetchNextPage();
		}

		return currentResult < results.size() - 1;
	}

	@SuppressWarnings("unchecked")
	protected void fetchNextPage() throws FreebaseServiceException {
		try {
			// String response = readService.readRaw(query, cursor);
			// System.out.println(jsonData);
			// Map<String, Object> data = (Map<String,
			// Object>)parser.read(response);
			// readService.parseServiceErrors(data);
			final Map<String, Object> q = (Map<String, Object>) readService
					.readRaw(query, cursor);

			// Map<String, Object> q = (Map<String,
			// Object>)res.get(query.getName());
			if (q.get("result") instanceof List) {
				final List<Object> r = (List<Object>) q.get("result");
				for (final Object obj : r) {
					results.add(new DefaultResult(query, obj));
				}
			} else {
				final Object obj = q.get("result");
				results.add(new DefaultResult(query, obj));
			}

			++numPages;
			final Object c = q.get("cursor");
			if (c != null) {
				cursor = c;
				LOG.info("CURSOR = " + cursor);
			}

			fetchedFirstPage = true;
		} catch (final IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public final ReadService getReadService() {
		return readService;
	}

}