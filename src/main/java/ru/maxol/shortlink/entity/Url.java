package ru.maxol.shortlink.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Url {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String url;

    @Column
    private String shortUrl;
    @Column
    private LocalDate dateOfCreation;

    public String getShortUrl() {
        return shortUrl;
    }

    public Url setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Url setUrl(String url) {
        this.url = url;
        return this;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public Url setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Url setId(Long id) {
        this.id = id;
        return this;
    }
}
