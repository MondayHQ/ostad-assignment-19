package com.example.urlshortener.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

// Local Imports
import com.example.urlshortener.models.URLEntity;

@Repository
public interface URLShortenerRepository extends JpaRepository<URLEntity, Long> {

    Optional<URLEntity> findByShortUrl(String shortUrl);

}
