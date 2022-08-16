package com.project.backend.blog.service;

import com.project.backend.blog.dto.BookmarkDto;
import com.project.backend.blog.entity.Bookmark;
import com.project.backend.blog.entity.BookmarkId;
import com.project.backend.blog.repository.BookMarkRepository;
import com.project.backend.common.error.BizException;
import com.project.backend.common.error.ErrorCode;
import com.project.backend.common.util.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;

    private final DataUtils dataUtils;

    /**
     * 사용자 북마크 블로그 리스트 조회
     * @param memberId
     * @return
     */
    public List<BookmarkDto> getBookMarkListByMember(String memberId){

        List<Bookmark> bookmarks = bookMarkRepository.findBookmarkByMemberId(memberId);

        return dataUtils.mapList(bookmarks, BookmarkDto.class);
    }

    /**
     * 북마크 데이터 단건 추가
     * @param bookmarkDto
     * @param memberId
     */
    @Transactional
    public void addBookMark(String memberId, BookmarkDto bookmarkDto){

        Bookmark bookmark = Bookmark.builder().memberId(memberId).bookmarkDto(bookmarkDto).build();

        //중복체크 Exception
        if(bookMarkRepository.existsById(new BookmarkId(memberId, bookmark.getBlogUrl()))){
            throw new BizException(ErrorCode.DUPLICATE_RESOURCE);
        }

        bookMarkRepository.save(bookmark);
    }

    /**
     * 북마크 데이터 단건 삭제
     * @param memberId
     * @param blogUrl
     */
    @Transactional
    public void deleteBookMark(String memberId, String blogUrl){

        Optional<Bookmark> optionalBookmark = bookMarkRepository.findById(new BookmarkId(memberId, blogUrl));

        //데이터 삭제, 미존재시 Exception 발행
        optionalBookmark.ifPresentOrElse(bookmark -> bookMarkRepository.delete(bookmark),
                () -> new BizException(ErrorCode.TARGET_DATA_NOT_FOUND));
    }

}
