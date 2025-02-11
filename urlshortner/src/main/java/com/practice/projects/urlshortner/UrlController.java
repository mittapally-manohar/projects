package com.practice.projects.urlshortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String longUrl) {
        return urlService.shortenUrl(longUrl);
    }

    @GetMapping("/expand")
    public String getLongUrl(@RequestParam String shortUrl) {
        return urlService.getLongUrl(shortUrl);
    }
}
