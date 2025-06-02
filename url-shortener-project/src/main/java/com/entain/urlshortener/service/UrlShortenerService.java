package com.entain.urlshortener.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entain.urlshortener.dto.ShortenResponse;
import com.entain.urlshortener.dto.StatsResponse;
import com.entain.urlshortener.exception.UrlNotFoundException;
import com.entain.urlshortener.model.UrlMapping;
import com.entain.urlshortener.repository.UrlMappingRepository;

@Service
public class UrlShortenerService {
	private final Map<String, UrlMapping> urlStore = new ConcurrentHashMap<>();
	private final Map<String, String> deduplicationMap = new ConcurrentHashMap<>();
	@Autowired
	private UrlMappingRepository repository;

	public UrlMapping createShortUrl(String originalUrl, String customCode, Instant expiresAt) {
		if (customCode == null || customCode.isBlank()) {
			if (deduplicationMap.containsKey(originalUrl)) {
				return urlStore.get(deduplicationMap.get(originalUrl));
			}
		}

		String shortCode = (customCode != null && !customCode.isBlank()) ? customCode : generateShortCode();
		if (urlStore.containsKey(shortCode)) {
			throw new IllegalArgumentException("Short code already in use");
		}

		UrlMapping mapping = new UrlMapping(null, originalUrl, shortCode, null, 0, null, false, shortCode, shortCode,
				shortCode, expiresAt);
		urlStore.put(shortCode, mapping);
		deduplicationMap.put(originalUrl, shortCode);
		return mapping;
	}

	public UrlMapping getMapping(String shortCode) {
		return urlStore.get(shortCode);
	}

	public boolean isValidUrl(String url) {
		try {
			URI.create(url).toURL();
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}

	private String generateShortCode() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 6);
	}

	public List<Map<String, Object>> getAllMappings() {
		return urlStore.values().stream().map(m -> {
			Map<String, Object> map = new HashMap<>();
			map.put("short_code", m.getShortCode());
			map.put("original_url", m.getOriginalUrl());
			map.put("created_at", m.getCreatedAt().toString());
			map.put("expires_at", m.getExpiresAt() != null ? m.getExpiresAt().toString() : null);
			map.put("visit_count", m.getVisitCount());
			return map;
		}).collect(Collectors.toList());
	}

	public ShortenResponse createShortUrl(String url) {
		String code = generateCode();
		UrlMapping entity = new UrlMapping();
		entity.setShortCode(code);
		entity.setOriginalUrl(url);
		entity.setCreatedAt(LocalDateTime.now());
		repository.save(entity);
		return new ShortenResponse("http://localhost:8080/" + code, code, url, entity.getCreatedAt());
	}

	public String getOriginalUrl(String code) {
		return repository.findByShortCode(code).map(shortUrl -> {
			shortUrl.setVisitCount(shortUrl.getVisitCount() + 1);
			repository.save(shortUrl);
			return shortUrl.getOriginalUrl();
		}).orElseThrow(() -> new UrlNotFoundException(code));
	}

	public StatsResponse getStats(String code) {
		UrlMapping entity = repository.findByShortCode(code).orElseThrow(() -> new UrlNotFoundException(code));
		return new StatsResponse(code, entity.getOriginalUrl(), entity.getCreatedAt(), entity.getVisitCount());
	}

	private String generateCode() {
		return RandomStringUtils.randomAlphanumeric(6);
	}
}
