
package com.jobportal.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("jobs")
public class Job {
 @Id private String id;
 private String title;
 private String description;
 private String location;
 private String salaryRange;
 private String jobType;
 private String employerId;
}
