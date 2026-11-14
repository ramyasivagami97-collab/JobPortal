package com.example.JobPortal.Service;

import com.example.JobPortal.ApplicationStatus;
import com.example.JobPortal.DTO.ApplicationResponseDTO;
import com.example.JobPortal.Entity.Application;
import com.example.JobPortal.Entity.Job;
import com.example.JobPortal.Entity.Users;
import com.example.JobPortal.Exception.*;
import com.example.JobPortal.Repository.ApplicationRepository;
import com.example.JobPortal.Repository.JobRepository;
import com.example.JobPortal.Repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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



    public List<ApplicationResponseDTO> getAllApplications() {
        List<Application> applicationList = applicationRepository.findAll();

        return applicationList.stream()
                .map(application -> new ApplicationResponseDTO(
                        application.getId(),
                        application.getAppliedDate(),
                        application.getStatus()
                )).toList();
    }

    public ApplicationResponseDTO getApplicationById(Long id){
        Application getApplicationId = applicationRepository.findById(id)
                .orElseThrow(() -> new applicationNotFoundExceptions("No Application Found!"));
        return new ApplicationResponseDTO(
                getApplicationId.getId(),
                getApplicationId.getAppliedDate(),
                getApplicationId.getStatus()
        );
    }


    public List<ApplicationResponseDTO> getApplicationsByUser(Long userId){
        List<Application> applicationsList = applicationRepository.findByUsersId(userId);

        if(applicationsList.isEmpty()){
            throw new noApplicationFoundForUserExceptions("No applications found for this user!");
        }

            return applicationsList.stream()
                    .map(application -> new ApplicationResponseDTO(
                            application.getId(),
                            application.getAppliedDate(),
                            application.getStatus()
                    )).toList();
    }

    public List<ApplicationResponseDTO> getApplicationsByJob(Long jobId){
       List<Application> applicationsJob = applicationRepository.findByJobId(jobId);

       if(applicationsJob.isEmpty()){
           throw new noApplicationFoundForJobExceptions("No applications found for this job!");
       }
       return applicationsJob.stream()
               .map(application -> new ApplicationResponseDTO(
                       application.getId(),
                       application.getAppliedDate(),
                       application.getStatus()
               )).toList();

    }
    public ApplicationResponseDTO applyForJob(Long jobId, Long userId){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new jobNotFoundExceptions("Job not Found!"));

        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new userNotFoundExceptions("User not found!"));

        if (applicationRepository.existsByUsersIdAndJobId(userId, jobId)) {
            throw new RuntimeException("User has already applied for this job!");
        }

        Application application = new Application();
        application.setJob(job);
        application.setUsers(users);
        application.setAppliedDate(LocalDate.now());

        application.setStatus(ApplicationStatus.APPLIED);

        Application savedApplication = applicationRepository.save(application);
        return new ApplicationResponseDTO(
                savedApplication.getId(),
                savedApplication.getAppliedDate(),
                savedApplication.getStatus()

        );

    }

    public ApplicationResponseDTO updateApplicationStatus(Long id, ApplicationStatus status){
       Application findApplication = applicationRepository.findById(id)
               .orElseThrow(() -> new applicationNotFoundExceptions("No Application Found!"));

        findApplication.setStatus(status);
       Application savedApplication = applicationRepository.save(findApplication);
       return new ApplicationResponseDTO(
               savedApplication.getId(),
               savedApplication.getAppliedDate(),
               savedApplication.getStatus()
       );
    }

    public Page<ApplicationResponseDTO>getApplicationPaginated(int page, int size) {
       Sort sort = Sort.by("appliedDate").ascending()
               .and(Sort.by("status").descending());
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Application> applicationPage = applicationRepository.findAll(pageable);

        return applicationPage.map(
                application -> new ApplicationResponseDTO(
                        application.getId(),
                        application.getAppliedDate(),
                        application.getStatus()
                )
        );

    }

    public void deleteApplication(Long id){
        if(!applicationRepository.existsById(id)){
            throw new applicationNotFoundExceptions("No Application Found!");
        }
        applicationRepository.deleteById(id);
    }
}
