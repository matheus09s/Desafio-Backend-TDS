package tds.company.api.controller;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpHeaders;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

        Optional<UrlEntity> url = urlRepository.findById(id);
        if (url.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            long totalAccesses = url.get().getAccessTimes().size();
            long daysSinceCreated = ChronoUnit.DAYS.between(url.get().getCreationTime(), LocalDateTime.now());
            double averageAccessesPerDay = daysSinceCreated > 0 ? (double) totalAccesses / daysSinceCreated : totalAccesses;

            UrlStats stats = new UrlStats(totalAccesses, averageAccessesPerDay);
            return ResponseEntity.ok(stats);
        }
    }
}








