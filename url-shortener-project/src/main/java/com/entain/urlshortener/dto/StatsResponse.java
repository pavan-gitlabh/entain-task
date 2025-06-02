package com.entain.urlshortener.dto;

import java.time.LocalDateTime;

public class StatsResponse {
	private String shortCode;
	private String originalUrl;
	private LocalDateTime createdAt;
	private int visitCount;

	public StatsResponse(String shortCode, String originalUrl, LocalDateTime createdAt, int visitCount) {
		super();
		this.shortCode = shortCode;
		this.originalUrl = originalUrl;
		this.createdAt = createdAt;
		this.visitCount = visitCount;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

}
