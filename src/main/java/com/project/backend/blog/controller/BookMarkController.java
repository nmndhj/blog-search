package com.project.backend.blog.controller;

import com.project.backend.blog.dto.BookmarkDto;
import com.project.backend.blog.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookMarkController :북마크(블로그 즐겨찾기) 조회/추가/삭제 API
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
@RequestMapping(value = "/bookmark")
public class BookMarkController {

    private final BookMarkService bookMarkService;

    //회원별 블로그 즐겨찾기 리스트 API
    @GetMapping
    public ResponseEntity<List<BookmarkDto>> getBokkmarkList(@RequestParam String memberId){
        return new ResponseEntity<>(bookMarkService.getBookMarkListByMember(memberId), HttpStatus.OK);
    }

    //블로그 즐겨찾기 추가 API
    @PostMapping
    public ResponseEntity<Object> addBookmark(@RequestParam String memberId, @RequestBody BookmarkDto bookmarkDto){
        bookMarkService.addBookMark(memberId, bookmarkDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //블로그 즐겨찾게 삭제 API
    @DeleteMapping
    public ResponseEntity<Object> deleteBookmark(@RequestParam String memberId, @RequestParam String blogUrl){
        bookMarkService.deleteBookMark(memberId, blogUrl);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
