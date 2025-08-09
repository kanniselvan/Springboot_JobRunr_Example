package com.kanni.jobrunrDemo.controller;

import com.kanni.jobrunrDemo.service.MyJobService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class JobController {

    private final JobScheduler jobScheduler;

    @Autowired
    public JobController(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
    }

    @GetMapping("/generate-job")
    public UUID generateJob() {
        return UUID.randomUUID();
    }

    @GetMapping("/enqueue-job")
    public String enqueueJob() {
        jobScheduler.enqueue(() -> new MyJobService().performBackgroundTask("Hello from JobRunr!"));
        return "Job enqueued!";
    }

    //These jobs run once at a future time and are identified by a JobId (UUID).
    @GetMapping("/schedule-job/{jobId}")
    public String scheduleJob(@PathVariable UUID jobId) {
        // Schedule a job to run in 1 minute
        jobScheduler.schedule(jobId,java.time.Instant.now().plusSeconds(60), () -> new MyJobService().performBackgroundTask("Scheduled Job!"));
        return "Job scheduled!. jobIdd=="+jobId;
    }

    //
    @GetMapping("/cancel-job/{jobId}")
    public String CancelJob(@PathVariable UUID jobId) {
        // Schedule a job to run in 1 minute
        jobScheduler.delete(jobId);
        return "✅ Job " + jobId + " deleted.";
    }
}
