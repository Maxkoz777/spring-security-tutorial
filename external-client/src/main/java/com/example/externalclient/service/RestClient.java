package com.example.externalclient.service;

import com.example.externalclient.model.UserModel;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.example.externalclient.utils.DataUtils.createUserModel;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Timer registerTimer;
    private final Timer simpleTimer;
    private final Timer loginTimer;

    @Value("${register.url}")
    private String registerUrl;
    @Value("${simple.url}")
    private String simpleUrl;
    @Value("${login.url}")
    private String loginUrl;

    public void sendRegisterRequest() {
        UserModel userModel = createUserModel();
        Timer.Sample sample = Timer.start();
        String response = restTemplate.postForObject(registerUrl, userModel, String.class);
        sample.stop(registerTimer);
        log.info(response);
    }

    public void sendSimpleRequest() {
        Timer.Sample sample = Timer.start();
        String response = restTemplate.getForObject(simpleUrl, String.class);
        sample.stop(simpleTimer);
        log.info(response);
    }

    public void sendLoginRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", "admin@admin.com");
        map.add("password", "admin");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        Timer.Sample sample = Timer.start();
        String response = restTemplate.postForObject(loginUrl, request, String.class);
        sample.stop(loginTimer);
        log.info(response);
    }
}
