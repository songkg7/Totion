package com.lambda.totion.service;

import com.lambda.totion.dto.AccessToken;
import com.lambda.totion.utils.Utils;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class TistoryService {

    @Value("${external.tistory_app_id}")
    private String APP_ID;

    @Value("${external.tistory_secret_key}")
    private String SECRET_KEY;

    private final String TISTORY_OAUTH_URI = "https://www.tistory.com/oauth";
    private final String redirectUrl = "http://localhost:8080/api/auth/access_token";

    public String auth() {
        log.info("TistoryService.auth");
        URI uri = UriComponentsBuilder.fromUriString(TISTORY_OAUTH_URI)
                .path("/authorize")
                .queryParam("client_id", APP_ID)
                .queryParam("redirect_uri", redirectUrl)
                .queryParam("response_type", "code")
                .encode()
                .build()
                .toUri();

        log.info("인증 요청 전송");

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);

    }

    public AccessToken getAccessToken(String authorizationCode) {
        log.info("accessToken 생성 요청");
        URI uri = UriComponentsBuilder.fromUriString(TISTORY_OAUTH_URI)
                .path("/access_token")
                .queryParam("client_id", APP_ID)
                .queryParam("client_secret", SECRET_KEY)
                .queryParam("redirect_uri", redirectUrl)  // 요청 검증을 위한 URL
                .queryParam("code", authorizationCode)
                .queryParam("grant_type", "authorization_code")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        Utils.ACCESS_TOKEN = restTemplate.getForObject(uri, AccessToken.class);
        log.info("accessToken 설정 완료");
        return Utils.ACCESS_TOKEN;
    }

}
