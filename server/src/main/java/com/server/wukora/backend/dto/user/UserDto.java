package com.server.wukora.backend.dto.user;

import com.server.wukora.backend.dto.skill.SkillDto;
import com.server.wukora.backend.dto.team.TeamDto;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    private String email;
    private String name;
    private List<String> roles;
    private Set<SkillDto> skills = new HashSet<>();
    private Set<TeamDto> appliedTeams = new HashSet<>();
    private String gitHubUrl;
    private String portfolioUrl;
    private String bio;
    private boolean lookingForTeam;
    private String profileUrl;
    private String bannerUrl;

}
