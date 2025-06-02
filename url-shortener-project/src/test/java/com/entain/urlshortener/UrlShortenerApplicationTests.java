package com.entain.urlshortener;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.entain.urlshortener.dto.ShortenResponse;
import com.entain.urlshortener.exception.UrlNotFoundException;
import com.entain.urlshortener.repository.UrlMappingRepository;
import com.entain.urlshortener.service.UrlShortenerService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UrlShortenerApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Mock
	private UrlMappingRepository repository;
	@InjectMocks
	private UrlShortenerService service;

	@Test
	public void testShortenUrl() throws Exception {
		String requestBody = "{\"url\":\"https://somevalidtest1.com\"}";

		mockMvc.perform(post("/api/shorten").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andExpect(jsonPath("$.short_url").exists());
	}

	@Test
	public void testCreateShortUrl() {
		String originalUrl = "https://somevalidtest1.com";
		when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));
		ShortenResponse res = service.createShortUrl(originalUrl);
		assertNotNull(res.getShortCode());
	}

	@Test
	public void testInvalidUrl() throws Exception {
		String requestBody = "{\"url\":\"invalid-url\"}";

		mockMvc.perform(post("/api/shorten").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testNotFoundRedirect() throws Exception {
		mockMvc.perform(get("/nonexistent")).andExpect(status().isNotFound());
	}

	@Test
	public void testStatsNotFound() {
		assertThrows(UrlNotFoundException.class, () -> service.getStats("abc123"));
	}
}
