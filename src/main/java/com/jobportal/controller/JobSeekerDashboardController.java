
package com.jobportal.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.jobportal.repository.*;
import com.jobportal.model.*;

@RestController
@RequestMapping("/api/seeker")
public class JobSeekerDashboardController {

 private final ApplicationRepository appRepo;

 public JobSeekerDashboardController(ApplicationRepository appRepo) {
  this.appRepo = appRepo;
 }

 @GetMapping("/applications/{userId}")
 public List<JobApplication> myApplications(@PathVariable String userId) {
  return appRepo.findByUserId(userId);
 }
}
