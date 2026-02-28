package com.example.urlshortener.services;

import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

// Local Imports
import com.example.urlshortener.models.URLEntity;
import com.example.urlshortener.dto.OriginalURLResponseDTO;
import com.example.urlshortener.dto.URLShortenerRequestDTO;
import com.example.urlshortener.dto.URLShortenerResponseDTO;
import com.example.urlshortener.exceptions.URLExpiredException;
import com.example.urlshortener.exceptions.URLNotFoundException;
import com.example.urlshortener.repositories.URLShortenerRepository;

@Service
public class URLShortenerService {

    private final URLShortenerRepository urlShortenerRepository;
    private final Base62Encoder base62Encoder;

    public URLShortenerService(URLShortenerRepository urlShortenerRepository, Base62Encoder base62Encoder) {
        this.urlShortenerRepository = urlShortenerRepository;
        this.base62Encoder = base62Encoder;
    }

    public URLShortenerResponseDTO createShortenURL(URLShortenerRequestDTO urlShortenerRequestDTO) {

        URLEntity urlEntity = new URLEntity();

        urlEntity.setUrl(urlShortenerRequestDTO.getUrl());
        urlEntity.setShortUrl("");
        urlEntity.setExpiresAt(urlShortenerRequestDTO.getExpiresAt());

        URLEntity savedURLEntity = urlShortenerRepository.save(urlEntity);

        Long id = savedURLEntity.getId();
        String shortenUrl = base62Encoder.encode(id);

        savedURLEntity.setShortUrl(shortenUrl);
        urlShortenerRepository.save(savedURLEntity);

        URLShortenerResponseDTO urlShortenerResponseDTO = new URLShortenerResponseDTO();

        urlShortenerResponseDTO.setOriginalUrl(urlShortenerRequestDTO.getUrl());
        urlShortenerResponseDTO.setShortUrl("http://localhost:8080/r/" + shortenUrl);
        urlShortenerResponseDTO.setExpiresAt(urlShortenerRequestDTO.getExpiresAt());

        return urlShortenerResponseDTO;
    }

    public OriginalURLResponseDTO getOriginalURL(String shortUrl) {

        Optional<URLEntity> urlEntity = urlShortenerRepository.findByShortUrl(shortUrl);

        if (urlEntity.isPresent()) {

            LocalDateTime expiresAt = urlEntity.get().getExpiresAt();
            if (expiresAt.isBefore(LocalDateTime.now())) {
                throw new URLExpiredException("url expired");
            }

            OriginalURLResponseDTO originalURLResponseDTO = new OriginalURLResponseDTO();

            originalURLResponseDTO.setOriginalURL(urlEntity.get().getUrl());
            originalURLResponseDTO.setExpiresAt(urlEntity.get().getExpiresAt());

            return originalURLResponseDTO;
        }

        throw new URLNotFoundException("URL not found");
    }

}
