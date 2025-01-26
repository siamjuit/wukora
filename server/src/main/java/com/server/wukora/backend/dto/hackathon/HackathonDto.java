package com.server.wukora.backend.dto.hackathon;

import com.server.wukora.backend.dto.team.TeamDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class HackathonDto {

    private String name;
    private LocalDateTime hackathonDate;
    private HackathonThemeDto theme;
    private LocalDateTime lastDateToRegister;
    private LocalDateTime postedOn = LocalDateTime.now();
    private Set<TeamDto> teamsApplied = new HashSet<>();

}
