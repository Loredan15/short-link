package ru.maxol.shortlink.controller;

import com.google.common.hash.Hashing;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maxol.shortlink.service.UrlService;
import ru.maxol.shortlink.utils.RequestUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("crop")
    public ResponseEntity<String> cropLink(HttpServletRequest req) {
        Map<String, String> bodyRequest = RequestUtils.getBodyRequest(req);
        final String url = bodyRequest.get("url");
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (urlValidator.isValid(url)) {
            final String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            urlService.saveCropUrl(url, id);
            return new ResponseEntity<>("http://localhost:8080/api/" + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{cropLink}")
    public ResponseEntity<Void> findAndRedirect(@PathVariable String cropLink) {
        var url = urlService.getFullUrl(cropLink);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
