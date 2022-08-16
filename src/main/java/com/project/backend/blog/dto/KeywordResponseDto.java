package com.project.backend.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KeywordResponseDto {

    private String keyName;         //키워드명

    private int searchCount;        //검색횟수

    @Builder
    public KeywordResponseDto(String keyName, int searchCount) {
        this.keyName = keyName;
        this.searchCount = searchCount;
    }
}
