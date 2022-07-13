package com.mysite.sbbb.answer.dao;

import com.mysite.sbbb.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
