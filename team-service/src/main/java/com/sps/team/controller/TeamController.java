package com.sps.team.controller;

import com.sps.team.dto.TeamDTO;
import com.sps.team.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Team REST Controller
 *
 * Exposes REST API endpoints for team management
 * Base URL: /api/teams
 */
@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Team Management", description = "APIs for managing cricket teams")
public class TeamController {

    private final TeamService teamService;

    /**
     * GET /api/teams
     * Get all teams
     */
    @GetMapping
    @Operation(summary = "Get all teams", description = "Retrieve a list of all teams")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        log.info("REST request to get all teams");
        List<TeamDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    /**
     * GET /api/teams/{id}
     * Get team by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get team by ID", description = "Retrieve a specific team by its ID")
    public ResponseEntity<TeamDTO> getTeamById(
            @Parameter(description = "Team ID") @PathVariable Long id) {
        log.info("REST request to get team by id: {}", id);
        TeamDTO team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    /**
     * POST /api/teams
     * Create new team
     */
    @PostMapping
    @Operation(summary = "Create new team", description = "Create a new cricket team")
    public ResponseEntity<TeamDTO> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        log.info("REST request to create team: {}", teamDTO.getTeamName());
        TeamDTO createdTeam = teamService.createTeam(teamDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    /**
     * PUT /api/teams/{id}
     * Update existing team
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update team", description = "Update an existing team")
    public ResponseEntity<TeamDTO> updateTeam(
            @Parameter(description = "Team ID") @PathVariable Long id,
            @Valid @RequestBody TeamDTO teamDTO) {
        log.info("REST request to update team: {}", id);
        TeamDTO updatedTeam = teamService.updateTeam(id, teamDTO);
        return ResponseEntity.ok(updatedTeam);
    }

    /**
     * DELETE /api/teams/{id}
     * Delete team
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete team", description = "Delete a team by its ID")
    public ResponseEntity<Map<String, String>> deleteTeam(
            @Parameter(description = "Team ID") @PathVariable Long id) {
        log.info("REST request to delete team: {}", id);
        teamService.deleteTeam(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Team deleted successfully");
        response.put("teamId", id.toString());

        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/teams/search?name={name}
     * Search teams by name
     */
    @GetMapping("/search")
    @Operation(summary = "Search teams", description = "Search teams by name (case-insensitive)")
    public ResponseEntity<List<TeamDTO>> searchTeams(
            @Parameter(description = "Team name to search") @RequestParam String name) {
        log.info("REST request to search teams by name: {}", name);
        List<TeamDTO> teams = teamService.searchTeamsByName(name);
        return ResponseEntity.ok(teams);
    }

    /**
     * GET /api/teams/coach?name={name}
     * Get teams by coach name
     */
    @GetMapping("/coach")
    @Operation(summary = "Get teams by coach", description = "Get all teams coached by a specific coach")
    public ResponseEntity<List<TeamDTO>> getTeamsByCoach(
            @Parameter(description = "Coach name") @RequestParam String name) {
        log.info("REST request to get teams by coach: {}", name);
        List<TeamDTO> teams = teamService.getTeamsByCoach(name);
        return ResponseEntity.ok(teams);
    }

    /**
     * GET /api/teams/age-limit?limit={limit}
     * Get teams by age limit
     */
    @GetMapping("/age-limit")
    @Operation(summary = "Get teams by age limit", description = "Get all teams with specific age limit")
    public ResponseEntity<List<TeamDTO>> getTeamsByAgeLimit(
            @Parameter(description = "Age limit") @RequestParam Integer limit) {
        log.info("REST request to get teams by age limit: {}", limit);
        List<TeamDTO> teams = teamService.getTeamsByAgeLimit(limit);
        return ResponseEntity.ok(teams);
    }

    /**
     * GET /api/teams/ordered
     * Get all teams ordered by age limit
     */
    @GetMapping("/ordered")
    @Operation(summary = "Get ordered teams", description = "Get all teams ordered by age limit")
    public ResponseEntity<List<TeamDTO>> getTeamsOrdered() {
        log.info("REST request to get teams ordered by age limit");
        List<TeamDTO> teams = teamService.getTeamsOrderedByAgeLimit();
        return ResponseEntity.ok(teams);
    }

    /**
     * GET /api/teams/count
     * Get total count of teams
     */
    @GetMapping("/count")
    @Operation(summary = "Get team count", description = "Get total number of teams")
    public ResponseEntity<Map<String, Long>> getTeamCount() {
        log.info("REST request to get team count");
        long count = teamService.getTeamCount();

        Map<String, Long> response = new HashMap<>();
        response.put("count", count);

        return ResponseEntity.ok(response);
    }
}
