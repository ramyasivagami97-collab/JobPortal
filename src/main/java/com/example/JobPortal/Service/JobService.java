package com.example.JobPortal.Service;

import com.example.JobPortal.DTO.JobRequestDTO;
import com.example.JobPortal.DTO.JobResponseDTO;
import com.example.JobPortal.Entity.Job;
import com.example.JobPortal.Entity.Skills;
import com.example.JobPortal.Exception.jobNotFoundExceptions;
import com.example.JobPortal.Exception.skillNotFoundExceptions;
import com.example.JobPortal.Repository.JobRepository;
import com.example.JobPortal.Repository.SkillsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final SkillsRepository skillsRepository;

    public JobService(JobRepository jobRepository, SkillsRepository skillsRepository) {
        this.jobRepository = jobRepository;
        this.skillsRepository = skillsRepository;
    }

    public JobResponseDTO addJob(JobRequestDTO request) {
        Job createdJob = new Job();
        createdJob.setTitle(request.title());
        createdJob.setDescription(request.description());
        createdJob.setLocation(request.location());
        createdJob.setSalary(request.salary());
        createdJob.setDepartment(request.department());

        Job savedJob = jobRepository.save(createdJob);

        return  new JobResponseDTO(
                savedJob.getId(),
                savedJob.getTitle(),
                savedJob.getDescription(),
                savedJob.getLocation(),
                savedJob.getSalary(),
                savedJob.getDepartment()
        );

    }
    public List<JobResponseDTO> getAllJobs(){
     List<Job> jobList = jobRepository.findAll();
     List<JobResponseDTO> responseList = new ArrayList<>();

     for(Job job: jobList){
         JobResponseDTO response = new JobResponseDTO(
                 job.getId(),
                 job.getTitle(),
                 job.getDescription(),
                 job.getLocation(),
                 job.getSalary(),
                 job.getDepartment()
         );
         responseList.add(response);
     }
     return responseList;
    }

    public JobResponseDTO getJobById(Long id){

        Job existingId = jobRepository.findById(id).orElseThrow(() -> new jobNotFoundExceptions("Job Not Found!"));

        return new JobResponseDTO(
                existingId.getId(),
                existingId.getTitle(),
                existingId.getDescription(),
                existingId.getLocation(),
                existingId.getSalary(),
                existingId.getDepartment()
        );
    }

    public JobResponseDTO updateJob(Long id, JobRequestDTO request){

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new jobNotFoundExceptions("Job Not Found!"));
        job.setTitle(request.title());
        job.setDescription(request.description());
        job.setLocation(request.location());
        job.setSalary(request.salary());
        job.setDepartment(request.department());

        Job updatedJob = jobRepository.save(job);

        return new JobResponseDTO(
                updatedJob.getId(),
                updatedJob.getTitle(),
                updatedJob.getDescription(),
                updatedJob.getLocation(),
                updatedJob.getSalary(),
                updatedJob.getDepartment()

        );

    }

    public void deleteJob(Long id){
        if(!jobRepository.existsById(id)){
            throw new jobNotFoundExceptions("Job Not Found!");
        }
        jobRepository.deleteById(id);
    }

    public List<JobResponseDTO> searchJobs(String title, String location, String department, Double salary){
        List<Job> jobList =jobRepository.searchJobs(title, location, department,salary);


        if(jobList.isEmpty()){
            throw new jobNotFoundExceptions("Job Not Found!");
        }

     return jobList.stream()
               .map(job -> new JobResponseDTO(
                       job.getId(),
                       job.getTitle(),
                       job.getDescription(),
                       job.getLocation(),
                       job.getSalary(),
                       job.getDepartment()

               ))
               .toList();

    }

    public JobResponseDTO addSkillToJob(Long jobId, Long skillId){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new jobNotFoundExceptions("Job Not Found!"));

        Skills skill = skillsRepository.findById(skillId)
                .orElseThrow(() -> new skillNotFoundExceptions("Skill Not Found!"));

        if(job.getSkills().contains(skill)){
            throw new RuntimeException("Skill is already added to this job");
        }
            job.getSkills().add(skill);

        Job savedSkillJob =  jobRepository.save(job);
        return new JobResponseDTO(
                savedSkillJob.getId(),
                savedSkillJob.getTitle(),
                savedSkillJob.getDescription(),
                savedSkillJob.getLocation(),
                savedSkillJob.getSalary(),
                savedSkillJob.getDepartment()
        );
    }

    public Page <JobResponseDTO> getPaginatedJobs(int page, int size){
        Sort sort = Sort.by("title").ascending()
                .and(Sort.by("salary").descending());
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Job> returnedJobs = jobRepository.findAll(pageable);

        return returnedJobs.map(job -> new JobResponseDTO(
                        job.getId(),
                        job.getTitle(),
                        job.getDescription(),
                        job.getLocation(),
                        job.getSalary(),
                        job.getDepartment()
                ));

    }
}
