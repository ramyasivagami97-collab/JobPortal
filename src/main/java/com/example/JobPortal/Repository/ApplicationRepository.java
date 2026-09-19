package com.example.JobPortal.Repository;

import com.example.JobPortal.Entity.Application;
import com.example.JobPortal.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {


    List<Application> findByUsersId(Long userId);

    List<Application> findByJobId(Long jobId);

    boolean existsByUsersIdAndJobId(Long userId, Long jobId);
}
