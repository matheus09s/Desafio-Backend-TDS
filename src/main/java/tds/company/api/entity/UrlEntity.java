package tds.company.api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="url")
public class UrlEntity {

    @Id
    private String id;
    @Column(nullable = false)
    private String longUrl;
    private LocalDateTime creationTime;
    @ElementCollection
    private List<LocalDateTime> accessTimes = new ArrayList<>();


    public UrlEntity() {
        this.accessTimes = new ArrayList<>();
        this.creationTime = LocalDateTime.now(); // Definindo a data de criação aqui
    }

    public UrlEntity(List<LocalDateTime> accessTimes, LocalDateTime creationTime, String longUrl, String id) {
        this.accessTimes = accessTimes;
        this.creationTime = creationTime;
        this.longUrl = longUrl;
        this.id = id;
    }

    public List<LocalDateTime> getAccessTimes() {
        return accessTimes;
    }

    public void setAccessTimes(List<LocalDateTime> accessTimes) {
        this.accessTimes = accessTimes;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
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
