package com.mysite.sbbb.files.domain;

import com.mysite.sbbb.question.domain.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity

public class Files {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;

@ManyToOne
    private Question question;
}
