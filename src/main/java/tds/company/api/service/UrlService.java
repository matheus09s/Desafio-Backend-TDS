package tds.company.api.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tds.company.api.dto.ShortenUrlRequest;
import tds.company.api.entity.UrlEntity;
import tds.company.api.repository.UrlRepository;

@Service
public class UrlService {

    UrlRepository urlRepository;


    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String convertShortUrl(ShortenUrlRequest shortenUrlRequest) {

        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));


        urlRepository.save(new UrlEntity(id, shortenUrlRequest.getLongUrl()));


        return id;






    }
}
