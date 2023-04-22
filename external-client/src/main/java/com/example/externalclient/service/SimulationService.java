package com.example.externalclient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SimulationService {
    private final RestClient restClient;

    @Value("${simulation.threads.number}")
    private Integer threadsNumber;

    public void simulate() {
        log.info("Simulation started");
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);
        for (int i = 0; i < threadsNumber; i++) {
            executorService.submit(this::execute);
        }
        executorService.shutdown();
    }

    private void execute() {
        for (int i = 0; i < 1000; i++) {
            restClient.sendRegisterRequest();
            restClient.sendSimpleRequest();
            restClient.sendLoginRequest();
        }
    }
}
