package com.mysite.sbbb.article.dao;

import com.mysite.sbbb.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}