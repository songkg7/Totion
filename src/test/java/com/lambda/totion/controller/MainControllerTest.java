package com.lambda.totion.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${external.apikey}")
    private String apikey;

    @DisplayName("1. 노션에서 블로그용으로 작성된 모든 게시글 가져오기")
    @Test
    void test_1() {

    }

}