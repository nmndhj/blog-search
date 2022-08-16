package com.project.backend.blog.service;

import com.project.backend.blog.client.KakaoApiClient;
import com.project.backend.blog.client.NaverApiClient;
import com.project.backend.blog.dto.BlogInfoDto;
import com.project.backend.blog.entity.PortalType;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogApiService {

    private final KakaoApiClient kakaoApiClient;

    private final NaverApiClient naverApiClient;

    static int KAKAO_MAX_PAGE = 50;
    static int NAVER_MAX_START = 1000;

    /**
     * 카카오 블로그 API 조회
     * 에러 발생시 FallbackToNaverApi 호출
     */
    @CircuitBreaker(name="kakaoCircuitBreaker", fallbackMethod = "FallbackToNaverApi")
    public List<BlogInfoDto> getKakaoBlogInfoList(String query, String sort, String page, String size){

        ResponseEntity<Object> response = kakaoApiClient.searchKakaoBlogList(query, sort, page, size);

        HashMap map = (HashMap) response.getBody();

        List<Object> objectList = (List<Object>) map.get("documents");

        List<BlogInfoDto> blogInfoList = objectList.stream().map(this::convertFromKakao).collect(Collectors.toList());

        return blogInfoList;
    }

    /**
     * 카카오 블로그 API 검색 결과 전체 페이지 수 구하기
     * 에러 발생시 fallbackPageCntNaverApi 호출
     */
    @CircuitBreaker(name="kakaoCircuitBreaker", fallbackMethod = "fallbackPageCntNaverApi")
    public int getPageCntKakaoApi(String query, String sort, String size){

        ResponseEntity<Object> response = kakaoApiClient.searchKakaoBlogList(query, sort, String.valueOf(KAKAO_MAX_PAGE), size);

        HashMap resultMap = (HashMap) response.getBody();

        //노출 가능한 블로그 건수
        HashMap meta = (HashMap<String, String>) resultMap.get("meta");
        int totalPagableCnt = (int) meta.get("pageable_count");

        //한 페이지 노출 건수
        int sizeInt = Integer.parseInt(size);

        //페이지 수
        int pages = totalPagableCnt/sizeInt;
        int result = (totalPagableCnt%sizeInt == 0) ? pages : pages+1;

        //최대 노출 페이지보다 크면 KAKAO_MAX_PAGE 리턴
        return (result>KAKAO_MAX_PAGE) ? KAKAO_MAX_PAGE : result;
    }

    /**
     * 카카오 연동 API 결과 Object를 BlogInfoDto로 변환
     */
    public BlogInfoDto convertFromKakao(Object o){

        HashMap<String, String> map = (HashMap) o;

        String url = map.get("url");

        BlogInfoDto blogInfo = BlogInfoDto.builder().blogName(map.get("blogname")).title(map.get("title"))
                .blogUrl(url)
                .contents(map.get("contents")).postedDate(map.get("datetime")).portalType(PortalType.KAKAO).build();

        return blogInfo;
    }

    public List<BlogInfoDto> FallbackToNaverApi(String query, String sort, String page, String display, Throwable t){
        log.error(t.getMessage());
        return getNaverBlogInfoList(query, sort, page, display);
    }

    /**
     * 네이버 블로그 API 조회
     */
    public List<BlogInfoDto> getNaverBlogInfoList(String query, String sort, String page, String display){

        //카카오 기준 -> 네이버 정렬 parm 값으로 매핑
        sort = sort.equals("recency") ? "date" : "sim";

        String start = String.valueOf((Integer.parseInt(page)-1)*(Integer.parseInt(display))+1);

        ResponseEntity<Object> response = naverApiClient.searchNaverBlogList(query, sort, start, display);

        HashMap map = (HashMap) response.getBody();

        List<Object> objectList = (List<Object>) map.get("items");

        List<BlogInfoDto> blogInfoList = objectList.stream().map(this::convertFromNaver).collect(Collectors.toList());

        return blogInfoList;
    }

    public int fallbackPageCntNaverApi(String query, String sort, String display, Throwable t){
        return getPageCntNaverApi(query, sort, display);
    }

    /**
     * 카카오 블로그 API 검색 결과 전체 페이지 수 구하기
     */
    public int getPageCntNaverApi(String query, String sort, String display){

        sort = sort.equals("recency") ? "date" : "sim";

        ResponseEntity<Object> response = naverApiClient.searchNaverBlogList(query, sort, "1", display);

        HashMap map = (HashMap) response.getBody();

        int total = (int) map.get("total");
        total = (total > NAVER_MAX_START + Integer.parseInt(display)) ? (NAVER_MAX_START + Integer.parseInt(display)) : total;

        //한 페이지 노출 건수
        int sizeInt = Integer.parseInt(display);

        int pages = total/sizeInt;

        return (total%sizeInt==0) ? pages : pages+1;
    }

    /**
     * 네이버 연동 API 결과 Object를 BlogInfoDto로 변환
     */
    public BlogInfoDto convertFromNaver(Object o){

        HashMap<String, String> map = (HashMap) o;

        BlogInfoDto blogInfo = BlogInfoDto.builder().blogName(map.get("bloggername")).title(map.get("title"))
                .blogUrl(map.get("link"))
                .contents(map.get("description")).postedDate(map.get("postdate")).portalType(PortalType.NAVER).build();
        return blogInfo;
    }
}