package com.mysite.sbbb.question.controller;

import com.mysite.sbbb.answer.AnswerForm;
import com.mysite.sbbb.question.QuestionForm;
import com.mysite.sbbb.question.domain.Question;
import com.mysite.sbbb.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/question")

public class QuestionController {
    @Autowired
    private QuestionService questionService;


    @RequestMapping("/list")
    public String showQuestions(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("question",paging);
        return "user/question";
    }

    @RequestMapping("/detail/{id}")
    public String showDetail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "user/question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "user/question_form";
    }

    @PostMapping("/create")
    public String questionPageCreate(Question question, List<MultipartFile> file)throws Exception{
        questionService.create(question, file);
        return "redirect:/question/list";
    }


    @PostMapping("/like/{id}")
    public String questionLike(@PathVariable("id") Integer id){
        this.questionService.setLike(id);
        return "redirect:/question/list";
    }

}
