package tds.company.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name="url")
public class UrlEntity {

    @Id
    private String id;
    @Column(nullable = false)
    private String longUrl;

    public UrlEntity() {}

    public UrlEntity(String id, String longUrl) {
        this.id = id;
        this.longUrl = longUrl;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }


}
