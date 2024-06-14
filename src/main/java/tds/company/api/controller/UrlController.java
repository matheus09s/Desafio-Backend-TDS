package tds.company.api.controller;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tds.company.api.dto.ShortenUrlRequest;
import tds.company.api.dto.ShortenUrlResponse;
import tds.company.api.repository.UrlRepository;
import tds.company.api.service.UrlService;

import java.net.URI;

@RestController
public class UrlController {

    private  UrlRepository urlRepository;

    private UrlService urlService;

    public UrlController(UrlRepository urlRepository, UrlService urlService) {
        this.urlRepository = urlRepository;
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest shortenUrlRequest) {
        var redirectUrl = urlService.convertShortUrl(shortenUrlRequest);
        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }
    @GetMapping("{id}")
  public ResponseEntity<Void> redirect(@PathVariable("id") String id){
        return urlService.redirection(id);

    }


    }



