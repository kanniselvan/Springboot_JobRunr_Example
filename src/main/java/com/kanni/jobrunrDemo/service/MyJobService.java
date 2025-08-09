package com.kanni.jobrunrDemo.service;

import org.springframework.stereotype.Service;

@Service
public class MyJobService {

    public void performBackgroundTask(String message) {
        System.out.println("Executing background task: " + message);
        // Simulate some work
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Background task completed."+java.time.Instant.now());
    }
}