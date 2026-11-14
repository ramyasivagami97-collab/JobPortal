package com.example.JobPortal.DTO;

import jakarta.validation.constraints.NotBlank;

public record SkillsRequestDTO(
        @NotBlank(message = "Name should not be blank!")
        String name
) {
}
