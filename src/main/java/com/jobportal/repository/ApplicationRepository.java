
package com.jobportal.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.jobportal.model.JobApplication;

public interface ApplicationRepository extends MongoRepository<JobApplication,String> {
 List<JobApplication> findByUserId(String userId);
 List<JobApplication> findByJobId(String jobId);
}
