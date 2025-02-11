package com.practice.projects.urlshortner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByLongUrl(String longUrl);
    Url findByShortUrl(String shortUrl);
}
