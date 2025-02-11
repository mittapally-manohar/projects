package com.practice.projects.urlshortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String longUrl) {
        //check if url already exists in db
        Url existingUrl = urlRepository.findByLongUrl(longUrl);
        if(existingUrl != null) {
            return existingUrl.getShortUrl();
        }

        //generate a new short url
        String shortUrl = generateShortUrl(longUrl);

        // Save the mapping to the database
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);
        urlRepository.save(url);

        return shortUrl;
    }

    private String generateShortUrl(String longUrl) {
        // Use a simple hash or UUID to generate a short URL
        String uuid = UUID.randomUUID().toString();
        return Base64.getUrlEncoder().encodeToString(uuid.getBytes()).substring(0,8);
    }

    public String getLongUrl(String shortUrl) {
       Url url = urlRepository.findByShortUrl(shortUrl);
       return url == null ? null : url.getLongUrl();
    }

}
