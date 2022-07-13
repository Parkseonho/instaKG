package com.mysite.sbbb.question.controller;

import com.mysite.sbbb.question.dao.QuestionRepository;
import com.mysite.sbbb.question.domain.Question;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/question")
@AllArgsConstructor //값을 자동으로 넣어주는?
public class QuestionController {

    private final QuestionRepository questionRepository;

    @RequestMapping("/list")
    public String showQuestions(Model model) {
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questions",questionList);
        return "/user/question";
    }

}
