package com.project.backend.blog.client;

import com.project.backend.blog.client.HeaderConfig.NaverApiHeaderConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver-api", url = "${feign.naver-api.url}", decode404 = true, configuration = {NaverApiHeaderConfig.class})
public interface NaverApiClient {

    @GetMapping
    ResponseEntity<Object> searchNaverBlogList(@RequestParam String query, @RequestParam String sort, @RequestParam String start, @RequestParam String display);
}