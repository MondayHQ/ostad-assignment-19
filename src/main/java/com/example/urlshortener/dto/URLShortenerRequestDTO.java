package com.example.urlshortener.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URLShortenerRequestDTO {

    @NotBlank
    @Size(max = 255)
    private String url;

    private LocalDateTime expiresAt;

}
