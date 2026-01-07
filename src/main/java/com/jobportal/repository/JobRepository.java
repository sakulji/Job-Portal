
package com.jobportal.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.jobportal.model.Job;

public interface JobRepository extends MongoRepository<Job,String> {

 List<Job> findByEmployerId(String employerId);

 @Query("{ '$and': [" +
        " { 'title': { $regex: ?0, $options: 'i' } }," +
        " { 'location': { $regex: ?1, $options: 'i' } }," +
        " { 'jobType': { $regex: ?2, $options: 'i' } }" +
        "] }")
 List<Job> advancedSearch(String keyword, String location, String jobType);
}
