package com.jk.chatgptapi.service;

import com.jk.chatgptapi.dto.GptApiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GptApiServiceImpl implements GptApiService {

    @Value("${chatgpt.apiKey}")
    private String API_KEY;
    @Value("${chatgpt.apiEndPoint}")
    private String API_ENDPOINT;
    @Value("${chatgpt.apiModel}")
    private String API_MODEL;
    @Value("${chatgpt.apiTemperature}")
    private Float API_TEMPERATURE;
    @Value("${chatgpt.apiMaxTokens}")
    private int API_MAX_TOKENS;

    @Override
    public String getCompletions(String prompt) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", API_MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("temperature", API_TEMPERATURE);
        requestBody.put("max_tokens", API_MAX_TOKENS);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<GptApiResponseDTO> response = restTemplate.postForEntity(API_ENDPOINT, requestEntity, GptApiResponseDTO.class);

        return response.getBody().getChoices().get(0).getText();
    }
}
