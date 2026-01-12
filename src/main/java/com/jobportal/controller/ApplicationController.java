package com.jobportal.controller;

import com.jobportal.model.JobApplication;
import com.jobportal.repository.ApplicationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    /**
     * =========================================================
     * APPLY FOR A JOB (JOB SEEKER ONLY)
     * =========================================================
     * URL: POST /applications/{jobId}
     * Role: JOB_SEEKER
     */
    @PostMapping("/{jobId}")
    public ResponseEntity<JobApplication> applyForJob(
            @PathVariable String jobId,
            Authentication authentication) {

        JobApplication application = new JobApplication();
        application.setJobId(jobId);
        application.setApplicantEmail(authentication.getName());
        application.setStatus("APPLIED");

        JobApplication savedApplication =
                applicationRepository.save(application);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedApplication);
    }

    /**
     * =========================================================
     * GET MY APPLICATIONS (JOB SEEKER)
     * =========================================================
     * URL: GET /applications/my
     */
    @GetMapping("/my")
    public ResponseEntity<List<JobApplication>> getMyApplications(
            Authentication authentication) {

        String applicantEmail = authentication.getName();

        List<JobApplication> applications =
                applicationRepository.findByApplicantEmail(applicantEmail);

        return ResponseEntity.ok(applications);
    }

    /**
     * =========================================================
     * UPDATE APPLICATION STATUS (EMPLOYER ONLY)
     * =========================================================
     * URL: PUT /applications/{applicationId}/status
     * Example: ?status=SHORTLISTED
     */
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<JobApplication> updateApplicationStatus(
            @PathVariable String applicationId,
            @RequestParam String status) {

        JobApplication application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus(status);
        JobApplication updated =
                applicationRepository.save(application);

        return ResponseEntity.ok(updated);
    }
}
