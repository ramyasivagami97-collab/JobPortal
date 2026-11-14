package com.example.JobPortal.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record JobRequestDTO(
        @NotBlank(message = "Title should not be blank!")
        String title,
        @NotBlank(message = "Description should not be blank!")
        String description,
        @NotBlank(message = "Location should not be blank!")
        String location,
        @NotNull(message = "Salary is required!")
        @Positive(message = "Salary must be greater than 0!")
        Double salary,
        @NotBlank(message = "Department should not be blank!")
        String department
) {
}
