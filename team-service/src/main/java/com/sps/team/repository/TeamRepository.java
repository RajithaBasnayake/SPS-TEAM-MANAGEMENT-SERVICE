package com.sps.team.repository;

import com.sps.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Team Repository Interface
 *
 * Provides database operations for Team entity
 * Extends JpaRepository for standard CRUD operations
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    /**
     * Find team by exact team name (case-sensitive)
     * @param teamName the team name to search for
     * @return Optional containing the team if found
     */
    Optional<Team> findByTeamName(String teamName);

    /**
     * Find teams by team name containing search string (case-insensitive)
     * @param teamName partial team name to search for
     * @return List of matching teams
     */
    List<Team> findByTeamNameContainingIgnoreCase(String teamName);

    /**
     * Find teams by coach name
     * @param coachName the coach name to search for
     * @return List of teams coached by the specified coach
     */
    List<Team> findByCoachName(String coachName);

    /**
     * Find teams by age limit
     * @param ageLimit the age limit to search for
     * @return List of teams with the specified age limit
     */
    List<Team> findByAgeLimit(Integer ageLimit);

    /**
     * Find teams with age limit less than or equal to specified value
     * @param ageLimit the maximum age limit
     * @return List of teams
     */
    List<Team> findByAgeLimitLessThanEqual(Integer ageLimit);

    /**
     * Check if team name already exists
     * @param teamName the team name to check
     * @return true if exists, false otherwise
     */
    boolean existsByTeamName(String teamName);

    /**
     * Custom query to find all teams ordered by age limit
     * @return List of teams ordered by age limit (nulls last)
     */
    @Query("SELECT t FROM Team t ORDER BY t.ageLimit ASC NULLS LAST")
    List<Team> findAllOrderedByAgeLimit();

    /**
     * Find teams by coach name containing search string
     * @param coachName partial coach name
     * @return List of matching teams
     */
    @Query("SELECT t FROM Team t WHERE LOWER(t.coachName) LIKE LOWER(CONCAT('%', :coachName, '%'))")
    List<Team> searchByCoachName(@Param("coachName") String coachName);

    /**
     * Count teams by age limit
     * @param ageLimit the age limit
     * @return count of teams
     */
    long countByAgeLimit(Integer ageLimit);
}
