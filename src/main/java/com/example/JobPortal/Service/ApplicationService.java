package com.example.JobPortal.Service;

import com.example.JobPortal.Entity.Application;
import com.example.JobPortal.Entity.Job;
import com.example.JobPortal.Entity.Users;
import com.example.JobPortal.Repository.ApplicationRepository;
import com.example.JobPortal.Repository.JobRepository;
import com.example.JobPortal.Repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UsersRepository usersRepository;

    public ApplicationService(ApplicationRepository applicationRepository, JobRepository jobRepository, UsersRepository usersRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.usersRepository = usersRepository;
    }


    public Application addApplication(Application application){
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id){
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application ID is not available"));
    }

    public Application updateApplication(Long id, Application application){
      if (!applicationRepository.existsById(id)){
          throw new RuntimeException("Application ID is not available");
      }
        application.setId(id);
      return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByUser(Long userId){
        List<Application> applications = applicationRepository.findByUsersId(userId);

        if(applications.isEmpty()){
            throw new RuntimeException("No applications found for this user");
        }
        return applications;
    }

    public List<Application> getApplicationsByJob(Long jobId){
       List<Application> applications = applicationRepository.findByJobId(jobId);

       if(applications.isEmpty()){
           throw new RuntimeException("No applications found for this job");
       }
       return applications;
    }
    public Application applyForJob(Long jobId, Long userId){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job is not available"));

        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User is not found"));

        if (applicationRepository.existsByUsersIdAndJobId(userId, jobId)) {
            throw new RuntimeException("User has already applied for this job");
        }

        Application application = new Application();
        application.setJob(job);
        application.setUsers(users);
        application.setAppliedDate(LocalDate.now());

        application.setStatus(com.example.JobPortal.ApplicationStatus.APPLIED);
        return applicationRepository.save(application);

    }
    public void deleteApplication(Long id){
        if(!applicationRepository.existsById(id)){
            throw new RuntimeException("Application ID is not available");
        }
        applicationRepository.deleteById(id);
    }
}
