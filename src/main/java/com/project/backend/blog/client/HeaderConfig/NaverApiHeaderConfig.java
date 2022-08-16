package com.project.backend.blog.client.HeaderConfig;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * NaverApiHeaderConfig : 네이버 API 헤더 Config
 * @author  ihyejin
 * @Version 1.0.0
 * @Date 2022/08/15
 * @Description :
 * ===========================================================================
 * DATE         AUTHOR          NOTE
 -----------------------------------------------------------------------------
 */

public class NaverApiHeaderConfig {

    @Value("${feign.naver-api.id}")
    private String id;

    @Value("${feign.naver-api.key}")
    private String key;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id",id);
            requestTemplate.header("X-Naver-Client-Secret",key);
        };
    }
}
