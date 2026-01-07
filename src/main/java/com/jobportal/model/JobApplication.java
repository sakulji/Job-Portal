
package com.jobportal.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("applications")
public class JobApplication {
 @Id private String id;
 private String jobId;
 private String userId;
 private String resumePath;
 private String status;
}
