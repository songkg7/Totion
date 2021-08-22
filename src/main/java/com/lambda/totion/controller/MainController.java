package com.lambda.totion.controller;

import com.lambda.totion.dto.RequestDto;
import com.lambda.totion.dto.ResponseDto;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    @Value("${external.apikey}")
    private String apikey;

    @Value("${external.notion_db}")
    private String notionDbID;

    // GET: https://api.notion.com/v1/blocks/c11e8f64-d017-4cf8-8284-2dbbca6b30d6/children
    @GetMapping
    public ResponseEntity<String> test() {
        log.info("MainController.test");
        URI uri = UriComponentsBuilder.fromUriString("https://api.notion.com")
                .path("/v1/blocks/c11e8f64-d017-4cf8-8284-2dbbca6b30d6/children")
                .encode()
                .build()
                .toUri();

        log.info("uri = {}", uri);

        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .header("Notion-Version", "2021-08-16")
                .header("Authorization", apikey)
                .build();

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(requestEntity, String.class);

    }

    /**
     * status 조건으로 노션에 작성된 게시글 정보 가져오기
     *
     * @param status Ready, Complete
     * @return notion table 에 작성한 게시글 데이터
     */
    @GetMapping("/{status}")
    public ResponseDto status(@PathVariable String status) {
        log.info("MainController.status");
        URI uri = UriComponentsBuilder.fromUriString("https://api.notion.com")
                .path("v1/databases/" + notionDbID + "/query")
                .encode()
                .build()
                .toUri();

        RequestDto requestDto = new RequestDto();
        // 필터 조건
        requestDto.setFilter(
                new RequestDto.Filter(
                        "Status", new RequestDto.Select(status)
                ));

        // 정렬 조건
        requestDto.setSorts(
                List.of(new RequestDto.Sort("Created time", "ascending"))
        );

        RequestEntity<RequestDto> requestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Notion-Version", "2021-08-16")
                .header("Authorization", apikey)
                .body(requestDto);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ResponseDto> result = restTemplate.exchange(requestEntity,
                ResponseDto.class);

        return result.getBody();

    }

}
