package com.project.backend.blog.entity;

import com.project.backend.blog.dto.BookmarkDto;
import com.project.backend.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@IdClass(BookmarkId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseEntity {
    @Id
    private String memberId;            //사용자 id

    @Id
    private String blogUrl;             //블로그 url

    private String blogName;            //블로그명

    @Enumerated(EnumType.STRING)
    private PortalType portalType;      //블로그 출처 포털

    @Builder
    public Bookmark(String memberId, BookmarkDto bookmarkDto) {
        this.memberId = memberId;
        this.blogUrl = bookmarkDto.getBlogUrl();
        this.blogName = bookmarkDto.getBlogName();
        this.portalType = bookmarkDto.getPortalType();
    }
}
