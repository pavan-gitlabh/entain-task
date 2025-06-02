package com.entain.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entain.urlshortener.model.UrlMapping;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
	Optional<UrlMapping> findByShortCode(String shortCode);

	Optional<UrlMapping> findByOriginalUrl(String originalUrl);
}
