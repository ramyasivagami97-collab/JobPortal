package com.example.JobPortal.DTO;

import com.example.JobPortal.UserRole;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        UserRole role
) {
}
