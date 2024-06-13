package tds.company.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import tds.company.api.dto.ShortenUrlRequest;
import tds.company.api.entity.Url;
import tds.company.api.repository.UrlRepository;

public class UrlService {

    @Autowired
    UrlRepository urlRepository;



    public String convertShortUrl(ShortenUrlRequest shortenUrlRequest) {
        Url url = new Url();

        url.


    }
}
