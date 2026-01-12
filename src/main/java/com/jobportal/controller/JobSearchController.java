
package com.jobportal.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.jobportal.repository.JobRepository;
import com.jobportal.model.Job;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/jobs")
public class JobSearchController {

 private final JobRepository repo;

 public JobSearchController(JobRepository repo) {
  this.repo = repo;
 }

 @GetMapping("/advanced-search")
 public List<Job> advancedSearch(
   @RequestParam(defaultValue="") String keyword,
   @RequestParam(defaultValue="") String location,
   @RequestParam(defaultValue="") String jobType) {

  return repo.advancedSearch(keyword, location, jobType);
 }
}
