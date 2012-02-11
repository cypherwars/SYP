package com.narphorium.freebase.services.exceptions;

public class FreebaseServiceTimeoutException extends FreebaseServiceException {

	private static final long serialVersionUID = 1239284148549874021L;

	public static final String ERRORCODE = "/api/status/error/mql/timeout";

	public FreebaseServiceTimeoutException(final String description,
			final String host, final int port, final double timeout) {
		super(ERRORCODE, description, host, port, timeout);
	}

}
