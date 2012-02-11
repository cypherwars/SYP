package com.narphorium.freebase.services;

import java.net.URL;

import org.apache.http.client.HttpClient;

import com.narphorium.freebase.results.SearchResultSet;
import com.narphorium.freebase.services.exceptions.FreebaseServiceException;

public class SearchService extends AbstractFreebaseService {

	public SearchService(final HttpClient httpClient) {
		super(httpClient);
	}

	public SearchService(final URL baseUrl, final HttpClient httpClient) {
		super(baseUrl, httpClient);
	}

	// TODO
	public final SearchResultSet search(final String query)
			throws FreebaseServiceException {
		return null;
	}

}
