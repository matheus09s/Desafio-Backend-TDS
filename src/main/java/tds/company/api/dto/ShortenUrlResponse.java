package tds.company.api.dto;


public class ShortenUrlResponse {

    String url;

    public ShortenUrlResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
