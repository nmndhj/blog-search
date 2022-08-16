package com.project.backend.blog.dto;

import com.project.backend.blog.entity.PortalType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BlogInfoDto {

    private String blogName;            //블로그명

    private String title;               //블로그 제목

    private String blogUrl;            //검색된 블로그 페이지 링크

    private String contents;            //컨텐츠 내용

    private String postedDate;          //게시된 날짜

    private PortalType portalType;      //포털 구분

    @Builder
    public BlogInfoDto(String blogName, String title, String blogUrl, String contents, String postedDate, PortalType portalType) {
        this.blogName = blogName;
        this.title = title;
        this.blogUrl = blogUrl;
        this.contents = contents;
        this.postedDate = postedDate;
        this.portalType = portalType;
    }
}