package com.entain.urlshortener.controller;

import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entain.urlshortener.model.UrlMapping;
import com.entain.urlshortener.service.UrlShortenerService;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService service;
   
    @PostMapping("/api/shorten")
    public ResponseEntity<?> createShortUrl(@RequestBody Map<String, String> payload) {
        String url = payload.get("url");
        String expireAfterMinutesStr = payload.get("expire_in_minutes");
        String customCode = payload.get("custom_code");

        if (!StringUtils.hasText(url) || !service.isValidUrl(url)) {
            return ResponseEntity.badRequest().body("Invalid URL");
        }

        Instant expiresAt = null;
        if (StringUtils.hasText(expireAfterMinutesStr)) {
            try {
                long minutes = Long.parseLong(expireAfterMinutesStr);
                expiresAt = Instant.now().plus(minutes, ChronoUnit.MINUTES);
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid expiration value");
            }
        }

        try {
            UrlMapping mapping = service.createShortUrl(url, customCode, expiresAt);
            return ResponseEntity.ok(Map.of(
					"short_url",
					"http://localhost:8080/" + mapping.getShortCode(),
                    "short_code", mapping.getShortCode(),
                    "original_url", mapping.getOriginalUrl(),
                    "created_at", mapping.getCreatedAt().toString(),
                    "expires_at", mapping.getExpiresAt() != null ? mapping.getExpiresAt().toString() : null
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        UrlMapping mapping = service.getMapping(shortCode);
        if (mapping == null) {
            return ResponseEntity.notFound().build();
        }
        if (mapping.isExpired()) {
            return ResponseEntity.status(HttpStatus.GONE).build();
        }
        mapping.incrementVisitCount();
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create(mapping.getOriginalUrl())).build();
    }

    @GetMapping("/api/stats/{shortCode}")
    public ResponseEntity<?> getStats(@PathVariable String shortCode) {
        UrlMapping mapping = service.getMapping(shortCode);
        if (mapping == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
                "short_code", mapping.getShortCode(),
                "original_url", mapping.getOriginalUrl(),
                "created_at", mapping.getCreatedAt().toString(),
                "expires_at", mapping.getExpiresAt() != null ? mapping.getExpiresAt().toString() : null,
                "visit_count", mapping.getVisitCount()
        ));
    }

    @GetMapping("/api/urls")
    public ResponseEntity<?> listAllUrls() {
        return ResponseEntity.ok(service.getAllMappings());
    }
}
