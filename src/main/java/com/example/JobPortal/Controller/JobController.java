package com.example.JobPortal.Controller;

import com.example.JobPortal.DTO.JobRequestDTO;
import com.example.JobPortal.DTO.JobResponseDTO;
import com.example.JobPortal.Entity.Job;
import com.example.JobPortal.Service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/jobs")
    public ResponseEntity<JobResponseDTO> addJob(@Valid @RequestBody JobRequestDTO request){
        JobResponseDTO addedJob = jobService.addJob(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedJob);

    }

    @GetMapping("/jobs")
    public List<JobResponseDTO> getAllJobs(){
        return jobService.getAllJobs();
    }
    @GetMapping("/jobs/search")
    public List<JobResponseDTO> searchJobs(@RequestParam(required = false) String title, @RequestParam(required = false) String location,
                                @RequestParam(required = false) String department, @RequestParam(required = false) Double salary){
        return jobService.searchJobs(title, location, department, salary);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable Long id){
        JobResponseDTO getById = jobService.getJobById(id);
        return ResponseEntity.ok(getById);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(@PathVariable Long id,@Valid @RequestBody JobRequestDTO request){
        JobResponseDTO updatedJob = jobService.updateJob(id, request);
        return ResponseEntity.ok(updatedJob);
    }
    @PostMapping("/jobs/{jobId}/skills/{skillId}")
    public ResponseEntity<JobResponseDTO> addSkillToJob(@PathVariable Long jobId, @PathVariable Long skillId){

        JobResponseDTO updatedJob = jobService.addSkillToJob(jobId, skillId);

        return ResponseEntity.ok(updatedJob);
    }

    @GetMapping("/jobs/paginated")
    public ResponseEntity<Page<JobResponseDTO>> getPaginatedJobs(@RequestParam(defaultValue = "0")  int page, @RequestParam(defaultValue = "10") int size){

        Page<JobResponseDTO> jobResponseDTOPage = jobService.getPaginatedJobs(page,size);
        return ResponseEntity.ok(jobResponseDTOPage);

    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job has been deleted!");
    }
}
