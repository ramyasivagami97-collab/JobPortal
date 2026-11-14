package com.example.JobPortal.DTO;

import jakarta.validation.constraints.NotBlank;

public record CompanyRequestDTO(
        @NotBlank(message = "Name should not be blank!")
         String name,
        @NotBlank(message = "Description should not be blank!")
         String description,
        @NotBlank(message = "Location should not be blank")
        String location
) {
}
