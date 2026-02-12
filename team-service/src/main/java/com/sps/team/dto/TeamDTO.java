package com.sps.team.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Team Data Transfer Object
 *
 * Used for API requests and responses
 * Separates internal entity structure from external API contract
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private Long teamId;

    @NotBlank(message = "Team name is required")
    @Size(min = 2, max = 50, message = "Team name must be between 2 and 50 characters")
    private String teamName;

    @Size(max = 100, message = "Coach name cannot exceed 100 characters")
    private String coachName;

    private Integer ageLimit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
