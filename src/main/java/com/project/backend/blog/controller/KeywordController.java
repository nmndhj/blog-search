package com.project.backend.blog.controller;

import com.project.backend.blog.dto.KeywordRequestDto;
import com.project.backend.blog.dto.KeywordResponseDto;
import com.project.backend.blog.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * KeywordController : 블로그 검색 키워드 관련 Rest Controller
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
@RequestMapping("/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    //검색키워드 Top 10 조회 API
    @GetMapping
    public List<KeywordResponseDto> getKeyList(){
        return keywordService.getKeyRankedList();
    }

    //검색 키워드 조회수 업데이트 API
    @PostMapping
    public ResponseEntity<Object> addKey(@RequestBody KeywordRequestDto key){
        keywordService.hitKeyword(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
