package com.practice.projects.urlshortner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ResponseEntity<?> handleShortUrlNotFoundException(ShortUrlNotFoundException ex, WebRequest request){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Short URL not Found");
        errorResponse.put("message", ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
