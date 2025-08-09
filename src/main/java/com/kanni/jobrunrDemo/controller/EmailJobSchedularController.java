package com.kanni.jobrunrDemo.controller;
import com.kanni.jobrunrDemo.service.EmailJobService;
import com.kanni.jobrunrDemo.service.MyJobService;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class EmailJobSchedularController {

    private final JobScheduler jobScheduler;
    private final EmailJobService emailJobService;

    public EmailJobSchedularController(JobScheduler jobScheduler, EmailJobService emailJobService) {
        this.jobScheduler = jobScheduler;
        this.emailJobService = emailJobService;
    }

    @GetMapping("/sendWelcome/{email}")
    public String sendWelcome(@PathVariable String email) {
        JobId jobId= jobScheduler.enqueue(() -> emailJobService.sendWelcomeEmail(email));
        return jobId.asUUID()+" Job scheduled for " + email;
    }

    @GetMapping("/subscribe/{email}")
    public String subscribe(@PathVariable String email) {
        String jobId=jobScheduler.scheduleRecurrently(Duration.ofSeconds(60), () -> emailJobService.sendWelcomeEmail(email));
        return "Job scheduled!. "+jobId;

    }

    @GetMapping("/un-subscribe/{jobId}")
    public String unSubscribe(@PathVariable String jobId) {
        jobScheduler.delete(jobId);
        return "Job unSubscribe!.";

    }


}
