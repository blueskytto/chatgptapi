package com.jk.chatgptapi.controller;

import com.jk.chatgptapi.dto.SearchForm;
import com.jk.chatgptapi.service.GptApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WebController {

    private final GptApiService gptApiService;

    @GetMapping(value = "/")
    public String CreateChatMainForm(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return "chat/main";
    }

    @PostMapping(value = "/search")
    public String SearchChatMain(@Valid SearchForm searchForm, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "chat/main :: #resultDiv";
        }
        log.debug("ChatGPT API Start");

        String rsData = gptApiService.getCompletions(searchForm.getPrompt());
        model.addAttribute("rsData", rsData);

        return "chat/main :: #resultDiv";
    }
}
