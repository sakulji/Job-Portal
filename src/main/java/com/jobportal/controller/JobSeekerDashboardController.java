package com.jobportal.controller;

import com.jobportal.model.JobApplication;
import com.jobportal.repository.ApplicationRepository;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobseeker")
public class JobSeekerDashboardController {

    private final ApplicationRepository applicationRepository;

    public JobSeekerDashboardController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("/applications")
    public List<JobApplication> myApplications(Authentication authentication) {
        return applicationRepository
                .findByApplicantEmail(authentication.getName());
    }
}
