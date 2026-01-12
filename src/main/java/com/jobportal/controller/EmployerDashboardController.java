package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class EmployerDashboardController {

    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    public EmployerDashboardController(JobRepository jobRepository,
                                       ApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    /**
     * ✅ Get all jobs posted by the logged-in employer
     * Uses JWT identity instead of employerId in URL (SECURE)
     */
    @GetMapping("/jobs")
    public List<Job> getMyJobs(Authentication authentication) {
        String employerEmail = authentication.getName();
        return jobRepository.findByEmployerId(employerEmail);
    }

    /**
     * ✅ Get all applications for a specific job
     */
    @GetMapping("/applications/{jobId}")
    public List<JobApplication> getApplicationsForJob(
            @PathVariable String jobId) {
        return applicationRepository.findByJobId(jobId);
    }
}
