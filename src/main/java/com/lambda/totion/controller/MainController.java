package com.lambda.totion.controller;

import com.lambda.totion.dto.ResponseDto;
import com.lambda.totion.service.NotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    private final NotionService notionService;

    /**
     * status 조건으로 노션에 작성된 게시글 정보 가져오기
     *
     * @param status Ready, Complete
     * @return notion table 에 작성한 게시글 데이터
     */
    @GetMapping("/{status}")
    public ResponseDto status(@PathVariable String status) {
        log.info("MainController.status");
        return notionService.getTableFilterBy(status);
    }

    // GET: https://api.notion.com/v1/blocks/c11e8f64-d017-4cf8-8284-2dbbca6b30d6/children
    @GetMapping("/bulletin/{bulletinId}")
    public ResponseDto getBulletin(@PathVariable String bulletinId) {
        return notionService.getBulletinById(bulletinId);
    }

    // 개별 게시글 내용 확인하기 - status() 를 통해 가져온 게시글의 아이디값 필요

}
