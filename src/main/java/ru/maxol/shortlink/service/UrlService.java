package ru.maxol.shortlink.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.maxol.shortlink.entity.Url;
import ru.maxol.shortlink.exception.NoFoundElementException;
import ru.maxol.shortlink.repository.UrlRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UrlService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UrlService.class);
    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public void saveCropUrl(String fullUrl, String id) {
        var url = new Url()
                .setUrl(fullUrl)
                .setShortUrl(id)
                .setDateOfCreation(LocalDate.now());
        repository.save(url);
    }

    public String getFullUrl(String cropUrl) {
        Url byShortUrl = Optional.ofNullable(repository.findByShortUrl(cropUrl)).orElseThrow(() -> {
            LOGGER.error("link not found");
            throw new NoFoundElementException("link not found");
        });
        return byShortUrl.getUrl();
    }
}

