package com.mysite.sbbb.files.service;

import com.mysite.sbbb.files.dao.FilesRepository;
import com.mysite.sbbb.files.domain.Files;
import com.mysite.sbbb.question.QuestionForm;
import com.mysite.sbbb.question.domain.Question;
import com.mysite.sbbb.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FilesService {
    private final FilesRepository filesRepository;
    private final QuestionService questionService;

    @Async
    public void upload(QuestionForm questionForm, List<MultipartFile> multiFileList){
        String root = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploadFiles";

        File fileCheck = new File(root);
        if (!fileCheck.exists()) fileCheck.mkdirs();

        List<Map<String, String>> fileList = new ArrayList<>();

        for(int i = 0; i < multiFileList.size(); i++){
            String originFile = multiFileList.get(i).getOriginalFilename();
            String ext = originFile.substring(originFile.lastIndexOf("."));
            String changeFile = UUID.randomUUID().toString() + ext;

            Map<String, String> map = new HashMap<>();
            map.put("originFile", originFile);
            map.put("changeFile", changeFile);

            fileList.add(map);
        }

        try{
            for(int i = 0; i < multiFileList.size(); i++){
                File uploadFile = new File(root + "\\" + fileList.get(i).get("changeFile"));
                multiFileList.get(i).transferTo(uploadFile);
            }
            uploadDB(fileList, questionForm);
            System.out.println("다중 파일 업로드 성공!");
        }catch(IllegalStateException | IOException e){
            System.out.println("다중 파일 업로드 실패 ㅠㅠ");
            for(int i = 0; i < multiFileList.size(); i++){
                new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
            }
            e.printStackTrace();
        }
    }

    private void uploadDB(List<Map<String, String>> fileList, QuestionForm questionForm) {
        Question question = questionService.create(questionForm.getContent());

        for(Map<String, String> file : fileList){
            Files files = new Files();
            files.setFilename(file.get("changeFile"));
            files.setQuestion(question);
            filesRepository.save(files);
        }
    }
}