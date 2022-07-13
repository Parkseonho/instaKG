package com.mysite.sbbb.article.controller;


import com.mysite.sbbb.article.dao.ArticleRepository;
import com.mysite.sbbb.article.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;



    @RequestMapping("/detail")
    @ResponseBody
    public Article showArticleDetail(@RequestParam long id, String name) {
        // RequestParam : HTTP 요청 파라미터를 받기 위해 사용(있어도 그만 없어도 그만)
        Optional<Article> article = articleRepository.findById(id);
        // Optional<T> : 복잡한 조건문 없이도 null 값으로 인해 발생하는 예외 처리할 수 있음
        return article.orElse(null);
        // .orElse : Optional을 통해 가져온 값이 null일때 해당값을 반환하는 메소드
        // 사용방법 : detail?id=번호
    }
    @RequestMapping("/findByTitle")
    @ResponseBody
    public Object showArticleTitle(@RequestParam String title) { //String과 List를 사용하니 Object 사용
        if (!articleRepository.existsByTitle(title)) {
            List<Article> article = articleRepository.findByTitle(title);
            if(article != null){ // 게시물에 내용이 없어도 "[]"는 존재함
                // 위의 if문을 통해서 title이 없는것중 null이 아닌것을 찾는거임
                return "없는 게시물입니다.";
            }
            return article;
        }
        List<Article> article = articleRepository.findByTitle(title);
        // 쿼리를 조합할 수 있음(findByTitle을 만든 후 메서드를 생성하면됨)
        return article;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showArticle(String title, String body) {
        if (title != null && body == null) { //title존재
          if(!articleRepository.existsByTitle(title)){ // title에 입력한 값과 게시물의 제목이 일치하지 않을때
              System.out.println("제목과 일치하는 게시물이 존재하지 않습니다."); // 화면이 아닌 intellij 실행창에 뜬다.
              return null;
          }
          return articleRepository.findByTitle(title); // title에 입력한 값과 게시물의 제목이 일치할 떄
        }else if (title == null && body != null) {
           if(!articleRepository.existsByBody(body)){
                System.out.println("내용과 일치하는 게시물이 존재하지 않습니다.");
                return null;
            }
            return articleRepository.findByBody(body);
        } else if (title != null && body != null) {
            if(!articleRepository.existsByTitleAndBody(title, body)){
                System.out.println("제목,내용과 일치하는 게시물이 존재하지 않습니다.");
                return null;
            }
            return articleRepository.findByTitleAndBody(title, body);
        }
        return articleRepository.findAll(); //아무것도 입력안하면 모든 게시물 출력
    }


    @RequestMapping("/doModify")
    @ResponseBody
    public Article showModify(@RequestParam long id, String title, String body) {
        Article article = articleRepository.findById(id).get();//조건에 맞는 데이터 가져오기

        if(title != null){
            article.setTitle(title);//불러온 데이터 수정
        }
        if(body != null){
            article.setBody(body);//불러온 데이터 수정
        }
            article.setUpdateDate(LocalDateTime.now());
        articleRepository.save(article);//수정된 데이터 DB에 저장
        // save() : 단순히 새 엔터티를 추가하는 메소드가 아닌 업데이트를 위한 용도로도 사용할 수 있다.
        return article;
    }
    @RequestMapping("/doWrite")
    @ResponseBody
    public Object showWrite(String title, String body) {
        if(title == null || title.trim().length() == 0){
            return "title을 입력하여주세요";
        }
        if(body == null|| body.trim().length() == 0){
            return "body를 입력하여주세요";
        }
        title = title.trim();
        body = body.trim();

        Article article = new Article();

        // id는 자동으로 설정
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);

        articleRepository.save(article);//수정된 데이터 DB에 저장
        // save() : 단순히 새 엔터티를 추가하는 메소드가 아닌 업데이트를 위한 용도로도 사용할 수 있다.

        return "%d번 게시물 생성 완료".formatted(article.getId());
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doArticleDelete(long id) {
        if (!articleRepository.existsById(id)) {
            return "이미 삭제되었거나 없는 게시물입니다.";
        }
        // existsById : 존재하는지 안 하는지 확인하기 위해 boolean을 확인하는 existsById
        // 만약 존재하지 않으면 이미 삭제되었다고 출력
        articleRepository.deleteById(id);
            return "%d번 게시물이 삭제되었습니다.".formatted(id);

        /*

        GDScript에서 텍스트를 형식화하는 방법
        [ 예시 ]
        var format_string = "We're waiting for {str}"

        var actual_string = format_string.format({"str": "Godot"})

        print(actual_string)
        # Output: "We're waiting for Godot"
        */
    }
}

/*
[crud] - 게시판 기본 구성
create

read

update

delete
 */