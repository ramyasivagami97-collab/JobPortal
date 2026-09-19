package com.example.JobPortal.Controller;

import com.example.JobPortal.Entity.Application;
import com.example.JobPortal.Service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApplicationController {

    private final ApplicationService applicationService;


    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/applications")
    public ResponseEntity<Application> addApplication(@RequestBody Application application){
        Application addedapplication = applicationService.addApplication(application);
        return ResponseEntity.ok(addedapplication);
    }

    @GetMapping("/applications")
    public List<Application> getAllApplications(){
        return applicationService.getAllApplications();
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id){
        Application applicationId = applicationService.getApplicationById(id);
        return ResponseEntity.ok(applicationId);
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id, @RequestBody Application application){
        Application updatedApplication = applicationService.updateApplication(id, application);
        return ResponseEntity.ok(updatedApplication);
    }

    @GetMapping("/users/{userId}/applications")
    public List<Application> getApplicationsByUser(@PathVariable Long userId){
        return applicationService.getApplicationsByUser(userId);
    }

    @GetMapping("/jobs/{jobId}/applications")
    public List<Application> getApplicationsByJob(@PathVariable Long jobId){
        return applicationService.getApplicationsByJob(jobId);
    }

    @PostMapping("/jobs/{jobId}/apply")
    public ResponseEntity<Application> applyForJob(
            @PathVariable Long jobId,
            @RequestParam Long userId) {

        Application application = applicationService.applyForJob(jobId, userId);

        return ResponseEntity.ok(application);
    }


    @DeleteMapping("/applications/{id}")
    public void deleteApplication(@PathVariable Long id){
        applicationService.deleteApplication(id);
    }
}
