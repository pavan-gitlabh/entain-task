package com.entain.urlshortener;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.entain.urlshortener.dto.ShortenResponse;
import com.entain.urlshortener.exception.UrlNotFoundException;
import com.entain.urlshortener.repository.UrlMappingRepository;
import com.entain.urlshortener.service.UrlShortenerService;

@ExtendWith(MockitoExtension.class)
public class ShortenerServiceTest {

	@Mock
	private UrlMappingRepository repository;
	@InjectMocks
	private UrlShortenerService service;

	@Test
	public void testCreateShortUrl() {
		String originalUrl = "https://example.com";
		when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));
		ShortenResponse res = service.createShortUrl(originalUrl);
		assertNotNull(res.getShortCode());
	}

	@Test
	public void testStatsNotFound() {
		assertThrows(UrlNotFoundException.class, () -> service.getStats("abc123"));
	}
}