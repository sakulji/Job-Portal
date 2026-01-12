package com.jobportal.repository;

import com.jobportal.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApplicationRepository
extends MongoRepository<JobApplication, String> {

List<JobApplication> findByJobId(String jobId);

List<JobApplication> findByApplicantEmail(String applicantEmail);
}
