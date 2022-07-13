package com.mysite.sbbb.answer.controller;

import com.mysite.sbbb.answer.dao.AnswerRepository;
import com.mysite.sbbb.answer.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping("/list")
    @ResponseBody
    public List<Answer> showAnswerList() {
        return answerRepository.findAll();
    }

}
