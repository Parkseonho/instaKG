package com.mysite.sbbb.question.controller;

import com.mysite.sbbb.question.domain.Question;
import com.mysite.sbbb.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/question")

public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/list")
    public String showQuestions(Model model) {
        List<Question> questionList = questionService.getList();
        model.addAttribute("questions",questionList);
        return "user/question";
    }

    @RequestMapping("/detail/{id}")
    public String showDetail(Model model, @PathVariable("id") Integer id){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "user/question_detail";
    }

    @GetMapping("/create")
    public String questionCreate( ){
        return "user/question_form";
    }
    @PostMapping("/create")
    public String questionCreate(@RequestParam String subject, @RequestParam String content){
        this.questionService.create(subject, content);
        return "redirect:/question/list";
    }

}
