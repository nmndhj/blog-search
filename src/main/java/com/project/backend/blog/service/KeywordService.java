package com.project.backend.blog.service;

import com.project.backend.blog.dto.KeywordRequestDto;
import com.project.backend.blog.dto.KeywordResponseDto;
import com.project.backend.blog.entity.Keyword;
import com.project.backend.blog.repository.KeywordRepository;
import com.project.backend.common.util.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeywordService {

    private final DataUtils dataUtils;
    private final KeywordRepository keywordRepository;

    /**
     * 키워드 미존재시 insert, 존재시 검색 횟수 +1
     */
    @Transactional
    public void hitKeyword(KeywordRequestDto keyword){

        Optional<Keyword> keywordOptional = keywordRepository.findById(keyword.getKeyword());

        keywordOptional.ifPresentOrElse(keyword1 -> keyword1.addSearchCount(),
                ()-> keywordRepository.save(new Keyword(keyword.getKeyword())));
    }

    /**
     * 많이 검색된 순으로 키워드 10개 조회
     */
    public List<KeywordResponseDto> getKeyRankedList(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "searchCount"));

        List<Keyword> keywords = keywordRepository.findAllBy(pageable);

        return dataUtils.mapList(keywords, KeywordResponseDto.class);
    }
}
