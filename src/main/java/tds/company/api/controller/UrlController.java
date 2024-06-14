package tds.company.api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tds.company.api.dto.ShortenUrlRequest;
import tds.company.api.dto.ShortenUrlResponse;
import tds.company.api.dto.UrlStats;
import tds.company.api.entity.UrlEntity;
import tds.company.api.repository.UrlRepository;
import tds.company.api.service.UrlService;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class UrlController {

    private UrlRepository urlRepository;

    private UrlService urlService;

    public UrlController(UrlRepository urlRepository, UrlService urlService) {
        this.urlRepository = urlRepository;
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest shortenUrlRequest) {
        String redirectUrl = urlService.convertShortUrl(shortenUrlRequest);
        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }


    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        ResponseEntity<UrlEntity> response = urlService.redirection(id);
        if (response.getStatusCode() == HttpStatus.FOUND && response.getBody() != null) {
            UrlEntity url = response.getBody();
            url.getAccessTimes().add(LocalDateTime.now());
            urlRepository.save(url);
            return ResponseEntity.status(HttpStatus.FOUND).headers(response.getHeaders()).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/stats/{id}")
    public ResponseEntity<UrlStats> getStats(@PathVariable("id") String id) {
        try {
            UrlStats stats = urlService.getStatsService(id);
            return ResponseEntity.ok(stats);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}










