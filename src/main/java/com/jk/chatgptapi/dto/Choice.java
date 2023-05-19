package com.jk.chatgptapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Choice {

    private String text;
    private Integer index;
    @JsonProperty("finish_reason")
    private String finishReason;
}
