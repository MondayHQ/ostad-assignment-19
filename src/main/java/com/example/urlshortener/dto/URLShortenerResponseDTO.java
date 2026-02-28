package com.example.urlshortener.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URLShortenerResponseDTO {

    private String shortUrl;
    private String originalUrl;
    private LocalDateTime expiresAt;

}
