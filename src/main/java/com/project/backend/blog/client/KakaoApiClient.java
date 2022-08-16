package com.project.backend.blog.client;

import com.project.backend.blog.client.HeaderConfig.KakaoApiHeaderConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao-api", url = "${feign.kako-api.url}", decode404 = true, configuration = {KakaoApiHeaderConfig.class})
public interface KakaoApiClient {

    @GetMapping
    ResponseEntity<Object> searchKakaoBlogList(@RequestParam String query, @RequestParam String sort, @RequestParam String page,@RequestParam String size);

}