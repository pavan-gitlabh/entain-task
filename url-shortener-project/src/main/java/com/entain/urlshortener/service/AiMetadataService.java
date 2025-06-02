package com.entain.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class AiMetadataService {

	public String generateSummary(String url) {

		return "This is a summary of the content at " + url;
	}

	public String detectLanguage(String url) {
		return "en";
	}

	public String detectSentiment(String url) {
		return "neutral";
	}
}