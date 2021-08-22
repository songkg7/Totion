package com.lambda.totion.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${external.apikey}")
    private String apikey;

    @DisplayName("1. notion 테이블에서 게시글 아이디값으로 내부 block 요소 가져오기")
    @Test
    void test_1() throws Exception {

        mockMvc.perform(get("/api")
                        .header("Notion-Version", "2021-08-16")
                        .header("Authorization", apikey))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @DisplayName("2. 노션에서 블로그용으로 작성된 모든 게시글 가져오기")
    @Test
    void test_2() {

    }

}