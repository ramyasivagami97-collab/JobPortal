package com.example.JobPortal.DTO;

public record JobResponseDTO(
        Long id,
        String title,
        String description,
        String location,
        Double salary,
        String department
) {
}
