package com.mysite.sbbb.answer;

import com.mysite.sbbb.DataNotFoundException;
import com.mysite.sbbb.answer.dao.AnswerRepository;
import com.mysite.sbbb.answer.domain.Answer;
import com.mysite.sbbb.question.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    public Answer getComment(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    public void create(Question question, String content){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setReplyLike(false);
        this.answerRepository.save(answer);
    }

    public void setLike(Integer answerId) {
        Answer answer = answerRepository.findById(answerId).get();
        if(answer.getReplyLike() == true) {
            answer.setReplyLike(false);
        } else {
            answer.setReplyLike(true);
        }
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer){
        this.answerRepository.delete(answer);
    }

    public void modify(Answer answer, String content, Boolean onOff){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
}
