package com.example.urlshortener.exceptions;

public class URLNotFoundException extends RuntimeException {

    public URLNotFoundException(String message) {
        super(message);
    }

}
