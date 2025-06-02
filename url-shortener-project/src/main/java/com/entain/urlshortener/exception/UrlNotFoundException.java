package com.entain.urlshortener.exception;

public class UrlNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4463074920648636598L;

	public UrlNotFoundException(String code) {
		super("Short code not found: " + code);
	}
}
