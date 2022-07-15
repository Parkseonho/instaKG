package com.mysite.sbbb.question.dao;

import com.mysite.sbbb.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
