package com.practice.projects.urlshortner;

public class ShortUrlNotFoundException extends RuntimeException{
    public ShortUrlNotFoundException(String message) {
        super(message);
    }
}
