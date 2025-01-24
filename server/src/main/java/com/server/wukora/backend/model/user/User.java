package com.server.wukora.backend.model.user;

import com.server.wukora.backend.model.skill.Skill;
import com.server.wukora.backend.model.team.Team;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;

    @Indexed( unique = true )
    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String name;

    private List<String> roles;

    @DBRef
    private Set<Skill> skills = new HashSet<>();

    @DBRef
    private Set<Team> appliedTeams = new HashSet<>();

    private String gitHubUrl;

    private String portfolioUrl;

    private String bio;

    private boolean lookingForTeam = false;

    private String profileUrl;

    private String bannerUrl;

}
