package com.practice.projects.urlshortner;

public class AliasAlreadyExistsException extends RuntimeException{
    public AliasAlreadyExistsException(String message) {
        super(message);
    }
}
