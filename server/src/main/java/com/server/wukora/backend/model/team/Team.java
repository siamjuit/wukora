package com.server.wukora.backend.model.team;

import com.server.wukora.backend.model.hackathon.Hackathon;
import com.server.wukora.backend.model.skill.Skill;
import com.server.wukora.backend.model.user.User;
import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "teams")
public class Team {

    @Id
    private ObjectId id;
    @NonNull
    private String name;
    private String description;
    @DBRef
    Set<Skill> requiredSkills = new HashSet<>();
    private int maxMembers;
    @DBRef
    private Set<User> teamMembers = new HashSet<>();
    @DBRef
    private Set<Hackathon> hackathonsApplied = new HashSet<>();

}
