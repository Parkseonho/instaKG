package com.mysite.sbbb.article.controller;

import com.mysite.sbbb.article.dao.ArticleRepository;
import com.mysite.sbbb.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showArticleList() {
        return articleRepository.findAll();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Article showArticleDetail(@RequestParam long id, String name) {
        //RequestParam : HTTP 요청 파라미터를 받기 위해 사용
        Optional<Article> article = articleRepository.findById(id);
        // Optional<T> : 복잡한 조건문 없이도 null 값으로 인해 발생하는 예외 처리할 수 있음
        return article.orElse(null);
        // .orElse : Optional을 통해 가져온 값이 null일때 해당값을 반환하는 메소드
        // 사용방법 : detail?id=번호
    }
}
