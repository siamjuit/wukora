package com.server.wukora.backend.request;

import com.server.wukora.backend.model.skill.Skill;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private List<String> roles = new ArrayList<>(List.of("ROLE_USER"));
    private Set<Skill> skills = new HashSet<>();
}
