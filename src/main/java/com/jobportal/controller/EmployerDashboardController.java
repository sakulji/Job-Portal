
package com.jobportal.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.jobportal.repository.*;
import com.jobportal.model.*;

@RestController
@RequestMapping("/api/employer")
public class EmployerDashboardController {

 private final JobRepository jobRepo;
 private final ApplicationRepository appRepo;

 public EmployerDashboardController(JobRepository jobRepo, ApplicationRepository appRepo) {
  this.jobRepo = jobRepo;
  this.appRepo = appRepo;
 }

 @GetMapping("/jobs/{employerId}")
 public List<Job> myJobs(@PathVariable String employerId) {
  return jobRepo.findByEmployerId(employerId);
 }

 @GetMapping("/applications/{jobId}")
 public List<JobApplication> applications(@PathVariable String jobId) {
  return appRepo.findByJobId(jobId);
 }
}
