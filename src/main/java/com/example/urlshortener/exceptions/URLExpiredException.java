package com.example.urlshortener.exceptions;

public class URLExpiredException extends RuntimeException {

    public URLExpiredException(String message) {
        super(message);
    }

}
