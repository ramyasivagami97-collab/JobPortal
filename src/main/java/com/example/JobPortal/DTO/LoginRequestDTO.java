package com.example.JobPortal.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @NotBlank(message = "Email Cannot be null or empty!")
        @Email(message = "Enter a valid email")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 15)
        String password
) {
}
