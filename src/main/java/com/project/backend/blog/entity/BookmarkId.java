package com.project.backend.blog.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkId implements Serializable {

    private static final long serialVersionUID = 3459633923378060042L;

    private String memberId;            //사용자 id

    private String blogUrl;             //블로그 url

    @Builder
    public BookmarkId(String memberId, String blogUrl) {
        this.memberId = memberId;
        this.blogUrl = blogUrl;
    }
}
