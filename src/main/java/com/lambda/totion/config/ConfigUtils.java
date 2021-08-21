package com.lambda.totion.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:env.yml")
public class ConfigUtils {

    private final Environment environment;

    // 환경변수 동적할당
    public String getApiKey() {
        return environment.getProperty("apikey");
    }

}
