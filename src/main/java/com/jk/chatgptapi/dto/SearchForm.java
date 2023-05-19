package com.jk.chatgptapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SearchForm {

    @NotNull
    private String prompt;

    private String rsData;

}
