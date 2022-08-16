package com.project.backend.blog.repository;

import com.project.backend.blog.entity.Keyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, String> {
    List<Keyword> findAllBy(Pageable pageable);
}
