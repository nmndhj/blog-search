package com.project.backend.blog.entity;

import com.project.backend.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword extends BaseEntity {

    @Id
    private String keyName;             //키워드명

    private int searchCount;            //검색횟수

    public Keyword(String keyName) {
        this.keyName = keyName;
        this.searchCount = 1;           //최초 검색 1회 초기화
    }

    /**
     * 키워드 검색 수 추가
     */
    public void addSearchCount(){
        this.searchCount++;
    }
}
