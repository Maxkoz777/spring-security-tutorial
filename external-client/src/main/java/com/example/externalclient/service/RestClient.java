package com.example.externalclient.service;

import com.example.externalclient.model.UserModel;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;

import static com.example.externalclient.utils.DataUtils.createUserModel;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Timer registerTimer;
    private final Timer simpleTimer;

    @Value("${register.url}")
    private String registerUrl;
    @Value("${simple.url}")
    private String simpleUrl;

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
}
