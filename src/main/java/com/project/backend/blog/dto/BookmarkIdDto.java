package com.project.backend.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkIdDto {

    private String memberId;            //사용자 id

    private String blogUrl;             //블로그 url
}
