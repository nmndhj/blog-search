package com.project.backend.blog.controller;

import com.project.backend.blog.dto.BlogInfoDto;
import com.project.backend.blog.service.BlogApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BlogController : Blog 정보 관련 RestController
 * @author  ihyejin
 * @Version 1.0.0
 * @Date 2022/08/15
 * @Description :
 * ===========================================================================
 * DATE         AUTHOR          NOTE
 -----------------------------------------------------------------------------
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogApiService blogService;

    //카카오 블로그 검색 API
    @GetMapping("/kakao")
    public List<BlogInfoDto> kakaoAll(@RequestParam String query, @RequestParam String sort, @RequestParam String page, @RequestParam String size){
        return blogService.getKakaoBlogInfoList(query, sort, page, size);
    }

    //카카오 블로그 검색 결과 페이지 수
    @GetMapping("/kakao/pages")
    public ResponseEntity<Integer> getKakaoPageCount(@RequestParam String query, @RequestParam String sort,@RequestParam String size){
        return new ResponseEntity<>(blogService.getPageCntKakaoApi(query, sort, size), HttpStatus.OK);
    }

    //네이버 블로그 검색 API
    @GetMapping("/naver")
    public List<BlogInfoDto> naver(@RequestParam String query, @RequestParam String sort, @RequestParam String page, @RequestParam String size){
        return blogService.getNaverBlogInfoList(query, sort, page, size);
    }

    //네이버 블로그 검색 결과 페이지 수
    @GetMapping("/naver/pages")
    public ResponseEntity<Integer> getNaverPageCount(@RequestParam String query, @RequestParam String sort,@RequestParam String size){
        return new ResponseEntity<>(blogService.getPageCntNaverApi(query, sort, size), HttpStatus.OK);
    }

}