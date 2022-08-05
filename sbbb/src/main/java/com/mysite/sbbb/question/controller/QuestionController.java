package com.mysite.sbbb.question.controller;

import com.mysite.sbbb.answer.AnswerForm;
import com.mysite.sbbb.answer.AnswerService;
import com.mysite.sbbb.question.QuestionForm;
import com.mysite.sbbb.question.domain.Question;
import com.mysite.sbbb.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/question")

public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;


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

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam("content") String content, BindingResult bindingResult){
        Question question = this.questionService.getQuestion(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "redirect:/question/list";
        }
        this.answerService.create(question, content);
        return "redirect:/question/list";
    }


    @PostMapping("/like/{id}")
    public String questionLike(@PathVariable("id") Integer id){
        this.questionService.setLike(id);
        return "redirect:/question/list";
    }

}
