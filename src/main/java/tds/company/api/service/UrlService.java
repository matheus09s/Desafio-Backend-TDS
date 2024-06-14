package tds.company.api.service;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tds.company.api.dto.ShortenUrlRequest;
import tds.company.api.entity.UrlEntity;
import tds.company.api.repository.UrlRepository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {

    HttpServletRequest servletRequest;
    UrlRepository urlRepository;


    public UrlService(HttpServletRequest servletRequest, UrlRepository urlRepository) {
        this.servletRequest = servletRequest;
        this.urlRepository = urlRepository;
    }

    public String convertShortUrl(ShortenUrlRequest shortenUrlRequest) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 7);
        } while (urlRepository.existsById(id));
        String urlredirect = servletRequest.getRequestURL().toString().replace("shorten-url", id);

        List<LocalDateTime> accessTimes = new ArrayList<>();
        LocalDateTime creationTime = LocalDateTime.now();
        UrlEntity urlEntity = new UrlEntity(accessTimes, creationTime, shortenUrlRequest.getLongUrl(), id);
        urlRepository.save(urlEntity);

        return urlredirect;
    }


    public ResponseEntity<UrlEntity> redirection (String id){
            var url = urlRepository.findById(id);
            if (url.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create(url.get().getLongUrl()));
                return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(url.get());
            }


        }
    }
