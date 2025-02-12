package com.practice.projects.urlshortner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String longUrl, String customAlias) throws InvalidAliasException, AliasAlreadyExistsException {

        if(customAlias != null || !customAlias.isEmpty()) {
            validateCustomAlias(customAlias);

            // Check if the alias is already taken
            if (urlRepository.findByShortUrl(customAlias) != null) {
                throw new AliasAlreadyExistsException("Custom alias is already in use.");
            }
            Url url = new Url();
            url.setLongUrl(longUrl);
            url.setShortUrl(customAlias);
            urlRepository.save(url);
            return customAlias;
        } else {
            //generate a new short url
            String shortUrl = generateShortUrl(longUrl);
            // Save the mapping to the database
            Url url = new Url();
            url.setLongUrl(longUrl);
            url.setShortUrl(shortUrl);
            urlRepository.save(url);
            return shortUrl;
        }

    }

    private void validateCustomAlias(String customAlias) throws InvalidAliasException {
        // Allow alphanumeric, underscores, and hyphens (adjust regex as needed)
        if (!customAlias.matches("^[a-zA-Z0-9_-]{3,20}$")) {
            throw new InvalidAliasException(
                    "Custom alias must be 3-20 characters long and contain only letters, numbers, underscores, or hyphens."
            );
        }
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
