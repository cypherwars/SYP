package com.narphorium.freebase.services;

import java.net.URL;

import org.apache.http.client.HttpClient;

import com.narphorium.freebase.services.exceptions.FreebaseServiceException;

public class UploadService extends AbstractFreebaseService {

	public UploadService(final HttpClient httpClient) {
		super(httpClient);
	}

	public UploadService(final URL baseUrl, final HttpClient httpClient) {
		super(baseUrl, httpClient);
	}

	// TODO
	public final void upload(final byte[] content)
			throws FreebaseServiceException {

	}

}
