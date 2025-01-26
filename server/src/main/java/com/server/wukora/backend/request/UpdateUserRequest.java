package com.server.wukora.backend.request;

import com.server.wukora.backend.model.skill.Skill;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserRequest {
    private String name;
    private String password;
    private Set<Skill> skills;
}
