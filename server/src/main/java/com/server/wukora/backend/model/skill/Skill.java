package com.server.wukora.backend.model.skill;

import com.server.wukora.backend.dto.team.TeamDto;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "skills")
public class Skill {

    @Id
    private ObjectId id;

    @NonNull
    private String name;

    @DBRef
    private Set<TeamDto> teams = new HashSet<>();

    private String icon;

}
