package com.mysite.sbbb.question.controller;

import com.mysite.sbbb.question.QuestionForm;
import com.mysite.sbbb.question.domain.Question;
import com.mysite.sbbb.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;


@Controller
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingController {
    private final QuestionService questionService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        this.questionService.delete(question);
        return "redirect:/board/post";
    }


    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, QuestionForm questionForm) {
        Question question = this.questionService.getQuestion(id);
        questionForm.setContent(questionForm.getContent());
        model.addAttribute("question", question);
        return "/modify";
    }

    @PostMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, @RequestParam(value = "onOff", required = false) Boolean onOff, @Valid QuestionForm questionForm, BindingResult bindingResult) {
        Question question = this.questionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "/modify";
        }
        this.questionService.modify(question, questionForm.getContent(), onOff);
        return String.format("redirect:/board/post/detail/%s", id);
    }


}

