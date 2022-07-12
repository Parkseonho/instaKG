package com.mysite.sbbb.article.dao;

import com.mysite.sbbb.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);

    boolean existsByTitle(String title);

}