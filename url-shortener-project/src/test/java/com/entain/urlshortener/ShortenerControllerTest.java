package com.entain.urlshortener;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ShortenerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testShortenEndpoint() throws Exception {
		mockMvc.perform(post("/api/shorten").contentType(MediaType.APPLICATION_JSON)
				.content("{\"url\": \"https://example.com\"}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.shortCode").exists());
	}

	@Test
	public void testRedirectNotFound() throws Exception {
		mockMvc.perform(get("/nonexist")).andExpect(status().isNotFound());
	}
}