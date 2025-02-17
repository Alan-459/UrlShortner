package com.UrlShortner.controller;

import com.UrlShortner.model.ShortUrl;
import com.UrlShortner.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ShortUrlController {

    @Autowired
    private  ShortUrlService service;


    // Shorten a URL
    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("url");
        ShortUrl shortUrl = service.createShortUrl(originalUrl);
        return ResponseEntity.ok(Map.of("shortUrl", "http://localhost:8080/api/" + shortUrl.getShortCode()));
    }

    //Redirect to Original URL
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode) {
        Optional<ShortUrl> shortUrl = service.getOriginalUrl(shortCode);
        return shortUrl.map(url -> ResponseEntity.status(302).location(URI.create(url.getOriginalUrl())).build())
                .orElse(ResponseEntity.notFound().build());
    }
}
