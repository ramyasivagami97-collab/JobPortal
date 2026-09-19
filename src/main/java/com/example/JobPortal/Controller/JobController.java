package com.example.JobPortal.Controller;

import com.example.JobPortal.Entity.Job;
import com.example.JobPortal.Service.JobService;
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
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        Job addedJob = jobService.addJob(job);
        return ResponseEntity.ok(addedJob);

    }

    @GetMapping("/jobs")
    public List<Job> getAllJobs(){
        return jobService.getAllJobs();
    }
    @GetMapping("/jobs/search")
    public List<Job> searchJobs(@RequestParam(required = false) String title, @RequestParam(required = false) String location,
                                @RequestParam(required = false) String department, @RequestParam(required = false) Double salary){
        return jobService.searchJobs(title, location, department, salary);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job getById = jobService.getJobById(id);
        return ResponseEntity.ok(getById);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job){
        Job updatedJob = jobService.updateJob(id, job);
        return ResponseEntity.ok(updatedJob);
    }
    @PostMapping("/jobs/{jobId}/skills/{skillId}")
    public ResponseEntity<Job> addSkillToJob(@PathVariable Long jobId, @PathVariable Long skillId){

        Job updatedJob = jobService.addSkillToJob(jobId, skillId);

        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
    }
}
