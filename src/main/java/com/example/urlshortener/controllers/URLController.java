package com.example.urlshortener.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// Local Imports
import com.example.urlshortener.dto.OriginalURLResponseDTO;
import com.example.urlshortener.dto.URLShortenerRequestDTO;
import com.example.urlshortener.dto.URLShortenerResponseDTO;
import com.example.urlshortener.services.URLShortenerService;

@Controller
@RequestMapping(path = "/r")
public class URLController {

    private final URLShortenerService urlShortenerService;

    public URLController(URLShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ResponseEntity<URLShortenerResponseDTO> createShortenURL(@Valid @RequestBody URLShortenerRequestDTO urlShortenerRequestDTO) {

        URLShortenerResponseDTO urlShortenerResponseDTO = urlShortenerService.createShortenURL(urlShortenerRequestDTO);

        return new ResponseEntity<>(urlShortenerResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{url}")
    public ResponseEntity<OriginalURLResponseDTO> getShortenURL(@PathVariable String url) {
        OriginalURLResponseDTO originalURLResponseDTO = urlShortenerService.getOriginalURL(url);

        return new ResponseEntity<>(originalURLResponseDTO, HttpStatus.OK);
    }

}
