package com.mysite.sbbb.files.Controller;

import com.mysite.sbbb.files.service.FilesService;
import com.mysite.sbbb.question.QuestionForm;
import com.mysite.sbbb.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilesController {
    private final FilesService filesService;
    private final QuestionService questionService;

    @GetMapping("/multi-file")
    public String showMultiForm(){
        return "/question";
    }

    @PostMapping("/multi-file")
    public String multiFileUpload(
            Model model,
            @RequestParam("multiFile") List<MultipartFile> multiFileList,
            QuestionForm questionForm
    )throws InterruptedException{
        try{
            filesService.upload(questionForm, multiFileList);
        }catch(Exception e){
            questionService.create(questionForm.getContent());
        }
        return "redirect:/question/list";
    }
}