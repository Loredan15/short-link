package ru.maxol.shortlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxol.shortlink.dto.UrlDto;
import ru.maxol.shortlink.entity.Url;
import ru.maxol.shortlink.repository.UrlRepository;

import java.time.LocalDate;

@Service
public class UrlService {
    private UrlRepository repository;
    private ConvertService convertService;

    @Autowired
    public void setRepository(UrlRepository repository) {
        this.repository = repository;
    }
@Autowired
    public void setConvertService(ConvertService convertService) {
        this.convertService = convertService;
    }

    public String cropUrl(UrlDto incomingRequest) {
        var url = new Url();
        url.setUrl(incomingRequest.getUrl());
        url.setDateOfCreation(LocalDate.now());
        var entity = repository.save(url);

        return convertService.encode(entity.getId());
    }

    public String getFullUrl(String cropUrl) {
        var id = convertService.decode(cropUrl);
        var entity = repository.findById(id);
        return entity.get().getUrl();
    }
}

