package com.mysite.sbbb.question.controller;

import com.mysite.sbbb.answer.AnswerForm;
import com.mysite.sbbb.answer.AnswerService;
import com.mysite.sbbb.question.QuestionForm;
import com.mysite.sbbb.question.domain.Question;
import com.mysite.sbbb.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/question")

public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;



    @RequestMapping("/list")
    public String showQuestions(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("question", questionList);
        return "user/question";
    }

    @RequestMapping("list/detail/{id}")
    public String showDetail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "user/question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "user/question_form";
    }

/*    @PostMapping("/create")
    public String questionPageCreate(Question question){
        questionService.create(question);
        return "redirect:/question/list";
    }*/

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult){
        Question question = this.questionService.getQuestion(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "user/question";
        }
        this.answerService.create(question, answerForm.getContent());
        return "redirect:/question/list";
    }

    @PostMapping("/like/{id}")
    public String questionLike(@PathVariable("id") Integer id){
        this.questionService.setLike(id);
        return "redirect:/question/list";
    }

}
