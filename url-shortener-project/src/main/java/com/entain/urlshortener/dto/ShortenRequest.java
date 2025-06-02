package com.entain.urlshortener.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public class ShortenRequest {

	@NotBlank
	@URL
	private String url;
}
