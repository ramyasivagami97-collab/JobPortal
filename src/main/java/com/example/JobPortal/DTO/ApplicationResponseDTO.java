package com.example.JobPortal.DTO;

import com.example.JobPortal.ApplicationStatus;

import java.time.LocalDate;

public record ApplicationResponseDTO(
        Long id,
        LocalDate appliedDate,
        ApplicationStatus status
) {
}
