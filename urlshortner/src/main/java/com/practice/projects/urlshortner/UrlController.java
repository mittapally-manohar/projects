package com.practice.projects.urlshortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/url/shorten")
    public String shortenUrl(@RequestParam String longUrl) {
        return urlService.shortenUrl(longUrl);
    }

    @GetMapping("/url/expand")
    public String getLongUrl(@RequestParam String shortUrl) {
        return urlService.getLongUrl(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortUrl) {
        String longUrl = urlService.getLongUrl(shortUrl);
        if(longUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", longUrl)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
