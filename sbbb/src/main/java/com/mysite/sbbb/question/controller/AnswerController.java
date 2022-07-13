package com.mysite.sbbb.question.controller;

import com.mysite.sbbb.question.dao.QuestionRepository;
import com.mysite.sbbb.question.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping("/list")
    @ResponseBody
    public List<Question> showQuestionList() {
        return questionRepository.findAll();
    }

}
