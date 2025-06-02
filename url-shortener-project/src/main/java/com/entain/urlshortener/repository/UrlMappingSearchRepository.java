package com.entain.urlshortener.repository;

import com.entain.urlshortener.model.UrlMapping;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMappingSearchRepository extends ElasticsearchRepository<UrlMapping, String> {
}
