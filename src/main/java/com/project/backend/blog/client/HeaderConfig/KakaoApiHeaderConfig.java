package com.project.backend.blog.client.HeaderConfig;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * KakaoApiHeaderConfig : 카카오 API 헤더 Config
 * @author  ihyejin
 * @Version 1.0.0
 * @Date 2022/08/15
 * @Description :
 * ===========================================================================
 * DATE         AUTHOR          NOTE
 -----------------------------------------------------------------------------
 */

public class KakaoApiHeaderConfig {

    @Value("${feign.kako-api.key}")
    private String key;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization","KakaoAK "+key);
        };
    }
}