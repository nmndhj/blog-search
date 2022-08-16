package com.project.backend.blog.repository;

import com.project.backend.blog.entity.Bookmark;
import com.project.backend.blog.entity.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<Bookmark, BookmarkId> {
    List<Bookmark> findBookmarkByMemberId(String memberId);
}