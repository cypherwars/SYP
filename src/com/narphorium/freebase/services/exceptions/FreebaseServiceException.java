package com.narphorium.freebase.services.exceptions;

public class FreebaseServiceException extends Exception {

	private static final long serialVersionUID = 1373399088812605290L;

	private final String code;
	private final String host;
	private final int port;
	private final double timeout;

	public FreebaseServiceException(final String code, final String message,
			final String host, final int port, final double timeout) {
		super(message);
		this.code = code;
		this.host = host;
		this.port = port;
		this.timeout = timeout;
	}

	public final String getCode() {
		return code;
	}

	public final String getHost() {
		return host;
	}

	public final int getPort() {
		return port;
	}

	public final double getTimeout() {
		return timeout;
	}

}
