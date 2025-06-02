package com.entain.urlshortener.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.entain.urlshortener.model.UrlMapping;

@Service
public class UrlSearchService {

	@Autowired
	private UrlMappingSearchRepository searchRepository;

	public void indexUrl(UrlMapping mapping) {
		searchRepository.save(mapping);
	}

	public List<UrlMapping> search(String keyword) {
		Page<UrlMapping> page = searchRepository.findAll(Pageable.unpaged());
		return page.stream()
				.filter(m -> (m.getSummary() != null && m.getSummary().toLowerCase().contains(keyword.toLowerCase())))
				.collect(Collectors.toList());

	}
}
