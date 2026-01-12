package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.repository.JobRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * =========================================================
     * CREATE JOB (EMPLOYER ONLY)
     * =========================================================
     * URL: POST /jobs
     * Role: EMPLOYER
     * Uses JWT to identify employer
     */
    @PostMapping
    public ResponseEntity<Job> createJob(
            @RequestBody Job job,
            Authentication authentication) {

        // Employer identity from JWT (email)
        String employerEmail = authentication.getName();

        // Set employerId securely
        job.setEmployerId(employerEmail);

        Job savedJob = jobRepository.save(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJob);
    }

    /**
     * =========================================================
     * GET ALL JOBS (PUBLIC)
     * =========================================================
     * URL: GET /jobs
     * Role: Public (Job seekers)
     */
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return ResponseEntity.ok(jobs);
    }

    /**
     * =========================================================
     * GET JOB BY ID (PUBLIC)
     * =========================================================
     * URL: GET /jobs/{jobId}
     */
    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable String jobId) {
        return jobRepository.findById(jobId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * =========================================================
     * DELETE JOB (EMPLOYER ONLY)
     * =========================================================
     * URL: DELETE /jobs/{jobId}
     * Only the job owner can delete
     */
    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(
            @PathVariable String jobId,
            Authentication authentication) {

        String employerEmail = authentication.getName();

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Security check: only owner can delete
        if (!job.getEmployerId().equals(employerEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not allowed to delete this job");
        }

        jobRepository.deleteById(jobId);
        return ResponseEntity.ok("Job deleted successfully");
    }
}
