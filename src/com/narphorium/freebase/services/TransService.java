package com.narphorium.freebase.services;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;

import com.narphorium.freebase.services.exceptions.FreebaseServiceException;

public class TransService extends AbstractFreebaseService {

	private static final Log LOG = LogFactory.getLog(TransService.class);

	public TransService(final HttpClient httpClient) {
		super(httpClient);
	}

	public TransService(final URL baseUrl, final HttpClient httpClient) {
		super(baseUrl, httpClient);
	}

	public final Image fetchImage(final String id)
			throws FreebaseServiceException {
		Image result = null;

		try {
			final String url = getUrlForId(id);
			final HttpGet method = new HttpGet(url);
			method.addHeader("User-Agent", USER_AGENT);
			final ResponseHandler<Image> handler = new ResponseHandler<Image>() {
				public Image handleResponse(HttpResponse response)
						throws IOException {
					final int status = response.getStatusLine().getStatusCode();

					if (status != HttpStatus.SC_OK) {
						throw new IOException(status + ": "
								+ response.getStatusLine().getReasonPhrase());
					}

					final HttpEntity entity = response.getEntity();
					if (entity != null) {
						return ImageIO.read(entity.getContent());
					} else {
						return null;
					}
				}
			};

			result = executeHttpRequest(method, handler);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}

		return result;
	}

	public final String fetchArticle(final String id) throws IOException,
			FreebaseServiceException {
		final String url = getUrlForId(id);
		return fetchPage(url);
	}

	private String getUrlForId(final String id) {
		String url = getBaseUrl() + "/trans/raw";

		if (id.startsWith("#")) {
			url += "/guid/" + id.substring(1);
		} else {
			url += id;
		}

		return url;
	}

}
