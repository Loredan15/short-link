package ru.maxol.shortlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maxol.shortlink.dto.UrlDto;
import ru.maxol.shortlink.service.UrlService;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UrlController {

    private UrlService urlService;

    @Autowired
    public void setUrlService(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("crop")
    public String cropLink(@RequestBody UrlDto urlDto){
        return urlService.cropUrl(urlDto);
    }

    @GetMapping("{cropLink}")
    public ResponseEntity<Void> findAndRedirect(@PathVariable String cropLink){
        var url = urlService.getFullUrl(cropLink);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
