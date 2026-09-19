package com.example.JobPortal.Repository;

import com.example.JobPortal.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {


    @Query("""
            SELECT j from Job j
            WHERE (:title IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:location IS NULL OR LOWER(j.location) = LOWER(:location))
              AND (:department IS NULL OR LOWER(j.department) = LOWER(:department))
                    AND (:salary IS NULL OR j.salary >= :salary)
            """)

    List<Job> searchJobs(
            @Param("title") String title,
            @Param("location") String location,
            @Param("department") String department,
            @Param("salary") Double salary
    );

}
