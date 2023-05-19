package com.jk.chatgptapi;

import com.jk.chatgptapi.dto.GptApiResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatgptapiApplicationTests {

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

    @Test
    void contextLoads() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", API_MODEL);
        requestBody.put("prompt", "Hello GPT");
        requestBody.put("temperature", API_TEMPERATURE);
        requestBody.put("max_tokens", API_MAX_TOKENS);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GptApiResponseDTO> response = restTemplate.postForEntity(API_ENDPOINT, requestEntity, GptApiResponseDTO.class);

        System.out.println(response.getBody().getChoices().get(0).getText());
    }

}
