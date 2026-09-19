package com.example.JobPortal.Service;

import com.example.JobPortal.Entity.Job;
import com.example.JobPortal.Entity.Skills;
import com.example.JobPortal.Repository.JobRepository;
import com.example.JobPortal.Repository.SkillsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final SkillsRepository skillsRepository;

    public JobService(JobRepository jobRepository, SkillsRepository skillsRepository) {
        this.jobRepository = jobRepository;
        this.skillsRepository = skillsRepository;
    }

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public Job getJobById(Long id){
        return jobRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Job ID is not available"));
    }

    public Job updateJob(Long id, Job job){
    if(!jobRepository.existsById(id)){
        throw new RuntimeException("Job ID is not available");
    }

    job.setId(id);
      return jobRepository.save(job);

    }

    public void deleteJob(Long id){
        if(!jobRepository.existsById(id)){
            throw new RuntimeException("Job ID is not available");
        }
        jobRepository.deleteById(id);
    }

    public List<Job> searchJobs(String title, String location, String department, Double salary){
        List<Job> jobs =jobRepository.searchJobs(title, location, department,salary);

        if(jobs.isEmpty()){
            throw new RuntimeException("No Jobs Available");
        }
        return jobs;
    }

    public Job addSkillToJob(Long jobId, Long skillId){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found!"));

        Skills skill = skillsRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found!"));

        if(job.getSkills().contains(skill)){
            throw new RuntimeException("Skill is already added to this job");
        }
        job.getSkills().add(skill);

        return jobRepository.save(job);
    }

}
