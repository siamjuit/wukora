package com.server.wukora.backend.dto.team;


import com.server.wukora.backend.dto.hackathon.HackathonDto;
import com.server.wukora.backend.dto.skill.SkillDto;
import com.server.wukora.backend.dto.user.UserDto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TeamDto {

    private String name;
    private String description;
    private Set<SkillDto> requiredSkills = new HashSet<>();
    private int maxMembers;
    private Set<UserDto> teamMembers = new HashSet<>();
    private Set<HackathonDto> hackathonsApplied = new HashSet<>();
}
