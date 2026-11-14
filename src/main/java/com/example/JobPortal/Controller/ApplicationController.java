package com.example.JobPortal.Controller;

import com.example.JobPortal.ApplicationStatus;
import com.example.JobPortal.DTO.ApplicationResponseDTO;
import com.example.JobPortal.Service.ApplicationService;
import org.springframework.data.domain.Page;
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

    @GetMapping("/applications")
    public List<ApplicationResponseDTO> getAllApplications(){
        return applicationService.getAllApplications();
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable Long id){
        ApplicationResponseDTO applicationId = applicationService.getApplicationById(id);
        return ResponseEntity.ok(applicationId);
    }



    @GetMapping("/users/{userId}/applications")
    public List<ApplicationResponseDTO> getApplicationsByUser(@PathVariable Long userId){
        return applicationService.getApplicationsByUser(userId);
    }

    @GetMapping("/jobs/{jobId}/applications")
    public List<ApplicationResponseDTO> getApplicationsByJob(@PathVariable Long jobId){
        return applicationService.getApplicationsByJob(jobId);
    }

    @PostMapping("/jobs/{jobId}/apply")
    public ResponseEntity<ApplicationResponseDTO> applyForJob(
            @PathVariable Long jobId,
            @RequestParam Long userId) {

        ApplicationResponseDTO application = applicationService.applyForJob(jobId, userId);

        return ResponseEntity.ok(application);
    }

    @PatchMapping("/applications/{id}/status")
    public ResponseEntity<ApplicationResponseDTO>updateApplicationStatus(@PathVariable Long id,@RequestBody ApplicationStatus applicationStatus){

        ApplicationResponseDTO application = applicationService.updateApplicationStatus(id,applicationStatus);
        return ResponseEntity.ok(application);
    }

    @GetMapping("/applications/paginated")
    public ResponseEntity<Page<ApplicationResponseDTO>>getApplicationPaginated(@RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "10") int size){
        Page<ApplicationResponseDTO> applicationResponseDTOPage =applicationService.getApplicationPaginated(page, size);
        return ResponseEntity.ok(applicationResponseDTOPage);
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id){
        applicationService.deleteApplication(id);
        return ResponseEntity.ok("Application has been deleted");
    }
}
