package com.mysite.sbbb.question.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mysite.sbbb.answer.domain.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Question {
    @Id

    private String filename;

    private String filepath;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private Integer hit;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;


    private Boolean replyLike;

    @Converter
    class BooleanToYNConverter implements AttributeConverter<Boolean, String> {
        @Override
        public String convertToDatabaseColumn(Boolean attribute) {
            return (attribute != null && attribute) ? "Y" : "N";
        }

        @Override
        public Boolean convertToEntityAttribute(String dbData) {
            return "Y".equals(dbData);
        }
    }
}