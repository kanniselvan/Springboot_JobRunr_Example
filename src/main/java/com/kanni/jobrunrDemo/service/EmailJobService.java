package com.kanni.jobrunrDemo.service;

import org.jobrunr.jobs.annotations.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailJobService {

    Logger logger=LoggerFactory.getLogger(EmailJobService.class);

    @Job(name = "Send welcome email to %0")
    public void sendWelcomeEmail(String email) {
        logger.info("Sending welcome email to: {} " , email);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {}
        logger.info("Email sent to {}" , email);
    }
}