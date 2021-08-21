package com.lambda.totion.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    @Value("${apikey}")
    private String apikey;

    // GET: https://api.notion.com/v1/blocks/c11e8f64-d017-4cf8-8284-2dbbca6b30d6/children
    @GetMapping
    public ResponseEntity<String> test() {
        URI uri = UriComponentsBuilder.fromUriString("https://api.notion.com")
                .path("/v1/blocks/c11e8f64-d017-4cf8-8284-2dbbca6b30d6/children")
                .encode()
                .build()
                .toUri();

        log.info("uri = {}", uri);
        log.info("MainController.test");

        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .header("Notion-Version", "2021-08-16")
                .header("Authorization", apikey)
                .build();

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(requestEntity, String.class);

    }

}
