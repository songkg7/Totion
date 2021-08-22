package com.lambda.totion.controller;

import com.lambda.totion.dto.AccessToken;
import com.lambda.totion.dto.ResponseDto;
import com.lambda.totion.dto.Result;
import com.lambda.totion.service.NotionService;
import com.lambda.totion.service.TistoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final NotionService notionService;
    private final TistoryService tistoryService;

    @GetMapping("/auth")
    public String auth() {
        return tistoryService.auth();  // 중간에 리다이렉트 요청이 발생하므로 여기서 리턴된 값은 사용 안됨
    }

    @GetMapping("/auth/access_token")
    public String redirectAuthorizationCode(@RequestParam("code") String authorizationCode,
            String state) {
        log.info("인증코드 반환");
        log.info("Authorization Code = {}", authorizationCode);

        AccessToken accessToken = tistoryService.getAccessToken(authorizationCode);
        log.info("accessToken = {}", accessToken.getAccessToken());
        return accessToken.getAccessToken();

    }

    // Notion 에 작성된 글 ID 가져오기
    @GetMapping("/table/{status}")
    public List<String> status(@PathVariable String status) {
        log.info("MainController.status");
        ResponseDto response = notionService.getTableFilterBy(status);

        // response 에서 id 만 가져오기
        List<String> collect = response.getResults().stream().map(Result::getId)
                .collect(Collectors.toList());

        return collect;

    }

    // notion 글 id 로 내용 가져오기
    @GetMapping("/bulletin/{bulletinId}")
    public ResponseDto getBulletin(@PathVariable String bulletinId) {
        return notionService.getBulletinById(bulletinId);
    }

}
