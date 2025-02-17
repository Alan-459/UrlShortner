package com.UrlShortner.service;

import com.UrlShortner.model.ShortUrl;
import com.UrlShortner.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ShortUrlService {
    @Autowired
    private ShortUrlRepository repository;
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;


    public ShortUrl createShortUrl(String originalUrl) {
        String shortCode = generateShortCode();
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setShortCode(shortCode);
        shortUrl.setOriginalUrl(originalUrl);
        return repository.save(shortUrl);
    }

    public Optional<ShortUrl> getOriginalUrl(String shortCode) {
        return repository.findByShortCode(shortCode);
    }

    private String generateShortCode() {
        Random random = new Random();
        StringBuilder shortCode = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            shortCode.append(CHARSET.charAt(random.nextInt(CHARSET.length())));
        }
        return shortCode.toString();
    }
}
