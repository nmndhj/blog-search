package com.project.backend.blog.dto;

import com.project.backend.blog.entity.BookmarkId;
import com.project.backend.blog.entity.PortalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkDto {

    private String blogUrl;             //블로그URL

    private String blogName;            //블로그명

    private PortalType portalType;      //블로그 출처 포털

    @Builder
    public BookmarkDto(BookmarkId bookmarkId, String blogName, PortalType portalType) {
        this.blogUrl = bookmarkId.getBlogUrl();
        this.blogName = blogName;
        this.portalType = portalType;
    }
}