package com.practice.projects.urlshortner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    private static final Logger logger = LoggerFactory.getLogger(UrlController.class);

    @Autowired
    private UrlService urlService;

    @PostMapping("/url/shorten")
    public ResponseEntity<?> shortenUrl(@RequestParam String longUrl, @RequestParam(required = false) String customAlias) {
        try {
            String shortUrl = urlService.shortenUrl(longUrl,customAlias);
            return ResponseEntity.ok(shortUrl);
        }catch(AliasAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (InvalidAliasException e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/url/expand")
    public String getLongUrl(@RequestParam String shortUrl) {
        return urlService.getLongUrl(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectToLongUrl(@PathVariable String shortUrl) {
        String longUrl = urlService.getLongUrl(shortUrl);
        if(longUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", longUrl)
                    .build();
        } else {
            logger.info("Short URL not found " + shortUrl);
            throw new ShortUrlNotFoundException("The requested short URL does not exist");
        }
    }
}
