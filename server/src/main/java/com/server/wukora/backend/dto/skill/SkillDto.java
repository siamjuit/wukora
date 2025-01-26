package com.server.wukora.backend.dto.skill;

import com.server.wukora.backend.dto.team.TeamDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SkillDto {

    private String name;
    private Set<TeamDto> teams = new HashSet<>();

}
