package com.entain.urlshortener.model;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Document(indexName = "url_mappings")
public class UrlMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Id
	@org.springframework.data.annotation.Id
	private String shortCode;
	private String originalUrl;
	private LocalDateTime createdAt;
	private int visitCount;
	private LocalDateTime expiry;
	private boolean custom;
	private String summary;
	private String language;
	private String sentiment;

	private Instant expiresAt;

	public UrlMapping(Long id, String shortCode, String originalUrl, LocalDateTime createdAt, int visitCount,
			LocalDateTime expiry, boolean custom, String summary, String language, String sentiment,
			Instant expiresAt) {
		super();
		this.id = id;
		this.shortCode = shortCode;
		this.originalUrl = originalUrl;
		this.createdAt = createdAt;
		this.visitCount = visitCount;
		this.expiry = expiry;
		this.custom = custom;
		this.summary = summary;
		this.language = language;
		this.sentiment = sentiment;
		this.expiresAt = expiresAt;
	}

	public UrlMapping() {

	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public String getShortCode() {
		return shortCode;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Instant getExpiresAt() {
		return expiresAt;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void incrementVisitCount() {
		this.visitCount++;
	}

	public boolean isExpired() {
		return expiresAt != null && Instant.now().isAfter(expiresAt);
	}

	public LocalDateTime getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDateTime expiry) {
		this.expiry = expiry;
	}

	public boolean isCustom() {
		return custom;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}
}
