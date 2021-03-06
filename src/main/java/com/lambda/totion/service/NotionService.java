package com.lambda.totion.service;

import com.lambda.totion.dto.notion.RequestDto;
import com.lambda.totion.dto.notion.ResponseDto;
import com.lambda.totion.dto.notion.ResponsePage;
import java.net.URI;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class NotionService {

    @Value("${external.apikey}")
    private String apikey;

    @Value("${external.notion_db}")
    private String notionDbID;

    private final String NOTION_API_URL = "https://api.notion.com";
    private final String NOTION_VERSION = "2021-08-16";

    public ResponseDto getTableFilterBy(String status) {
        log.info(status);
        URI uri = UriComponentsBuilder.fromUriString(NOTION_API_URL)
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
                .header("Notion-Version", NOTION_VERSION)
                .header("Authorization", apikey)
                .body(requestDto);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ResponseDto> result = restTemplate.exchange(requestEntity,
                ResponseDto.class);

        return result.getBody();
    }

    public ResponsePage getBulletinById(String bulletinId) {
        URI uri = UriComponentsBuilder.fromUriString(NOTION_API_URL)
                .path("/v1/blocks/" + bulletinId + "/children")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .header("Notion-Version", NOTION_VERSION)
                .header("Authorization", apikey)
                .build();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ResponsePage> result = restTemplate.exchange(requestEntity,
                ResponsePage.class);

        return result.getBody();
    }

}
