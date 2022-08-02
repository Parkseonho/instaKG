package com.mysite.sbbb.Files;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FilesForm {
    @NotEmpty(message = "내용은 필수")
    private String content;
}
