package com.server.wukora.backend.dto.hackathon;

import com.server.wukora.backend.dto.team.TeamDto;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class HackathonDto {

    private ObjectId id;
    private String name;
    private String description;
    private LocalDateTime hackathonDate;
    private HackathonThemeDto theme;
    private LocalDateTime lastDateToRegister;
    private LocalDateTime postedOn = LocalDateTime.now();
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<TeamDto> teamsApplied = new HashSet<>();
    private List<String> prizes = new ArrayList<>();

}
