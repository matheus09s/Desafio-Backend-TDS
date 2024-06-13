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
         String urlredirect= servletRequest.getRequestURL().toString().replace("shorten-url", id);

        urlRepository.save(new UrlEntity(id, shortenUrlRequest.getLongUrl()));

        return urlredirect;

    }
    public HttpHeaders redirection(String id){

        var url = urlRepository.findById(id);
        if (url.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build().getHeaders();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getLongUrl()));

        return headers;






    }
}
