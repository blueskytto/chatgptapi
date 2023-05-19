package com.jk.chatgptapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SearchForm {

    @NotEmpty(message = "내용 입력은 필수 입니다")
    private String prompt;

    private String rsData;

}
