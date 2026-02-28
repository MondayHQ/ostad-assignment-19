package com.example.urlshortener.services;

import org.springframework.stereotype.Service;

@Service
public class Base62Encoder {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = CHARACTERS.length();

    public String encode(long input) {
        StringBuilder sb = new StringBuilder();

        while (input > 0) {
            sb.append(CHARACTERS.charAt((int) (input % BASE)));
            input /= BASE;
        }

        return sb.reverse().toString();
    }
}
