package com.server.wukora.backend.request;

import com.server.wukora.backend.model.skill.Skill;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String name;
    private String password;
    private Set<Skill> skills;
}
