package com.example.JobPortal.Entity;

import com.example.JobPortal.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate appliedDate;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonBackReference("job-applications")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-applications")
    private Users users;

}
