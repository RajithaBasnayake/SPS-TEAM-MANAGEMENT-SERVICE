package com.sps.team.mapper;

import com.sps.team.dto.TeamDTO;
import com.sps.team.entity.Team;
import org.springframework.stereotype.Component;

/**
 * Team Mapper
 *
 * Converts between Team entity and TeamDTO
 * Implements manual mapping (can be replaced with MapStruct in production)
 */
@Component
public class TeamMapper {

    /**
     * Convert Team entity to TeamDTO
     * @param team the Team entity
     * @return TeamDTO
     */
    public TeamDTO toDTO(Team team) {
        if (team == null) {
            return null;
        }

        TeamDTO dto = new TeamDTO();
        dto.setTeamId(team.getTeamId());
        dto.setTeamName(team.getTeamName());
        dto.setCoachName(team.getCoachName());
        dto.setAgeLimit(team.getAgeLimit());
        dto.setCreatedAt(team.getCreatedAt());
        dto.setUpdatedAt(team.getUpdatedAt());

        return dto;
    }

    /**
     * Convert TeamDTO to Team entity
     * @param dto the TeamDTO
     * @return Team entity
     */
    public Team toEntity(TeamDTO dto) {
        if (dto == null) {
            return null;
        }

        Team team = new Team();
        team.setTeamId(dto.getTeamId());
        team.setTeamName(dto.getTeamName());
        team.setCoachName(dto.getCoachName());
        team.setAgeLimit(dto.getAgeLimit());
        team.setCreatedAt(dto.getCreatedAt());
        team.setUpdatedAt(dto.getUpdatedAt());

        return team;
    }

    /**
     * Update existing Team entity from TeamDTO
     * @param team existing Team entity to update
     * @param dto TeamDTO with new values
     */
    public void updateEntityFromDTO(Team team, TeamDTO dto) {
        if (team != null && dto != null) {
            team.setTeamName(dto.getTeamName());
            team.setCoachName(dto.getCoachName());
            team.setAgeLimit(dto.getAgeLimit());
        }
    }
}
