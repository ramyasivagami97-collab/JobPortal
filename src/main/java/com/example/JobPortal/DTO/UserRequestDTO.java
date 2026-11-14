package com.example.JobPortal.DTO;

import com.example.JobPortal.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "Name Cannot be null or empty!")
        String name,
        @Email(message = "Please provide a valid email address!")
        @NotBlank(message = "Email is required!")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 15)
        String password,
        @NotNull
        UserRole role
) {
}
