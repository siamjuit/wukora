package com.server.wukora.backend.dto.skill;

import com.server.wukora.backend.dto.team.TeamDto;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Set;

@Data
public class SkillDto {

    private ObjectId id;
    private String name;
    // private Set<TeamDto> teams = new HashSet<>();
    private String icon;
}
