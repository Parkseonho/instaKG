package com.mysite.sbbb.Files;

import com.mysite.sbbb.Files.dao.FilesRepository;
import com.mysite.sbbb.Files.domain.Files;
import com.mysite.sbbb.question.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilesService {

    @Autowired
    private final FilesRepository filesrepository;

    public void create(Question question, List<MultipartFile> filesList) throws Exception {

        try {
            Files files = new Files();
            for (MultipartFile file : filesList) {
                String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

                UUID uuid = UUID.randomUUID();

                String fileName = uuid + "_" + file.getOriginalFilename();

                File saveFile = new File(projectPath, fileName);

                file.transferTo(saveFile);

                files.setFilename(fileName);

                files.setFilepath("/files/" + fileName);

                files.setQuestion(question);

                this.filesrepository.save(files);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
