package com.jk.chatgptapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class GptApiResponseDTO {
    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;
}
