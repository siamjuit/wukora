package com.server.wukora.backend.model.hackathon;


import com.server.wukora.backend.model.team.Team;
import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "hackathons")
public class Hackathon {

    @Id
    private ObjectId id;

    @NonNull
    private String name;

    @NonNull
    private LocalDateTime hackathonDate;

    @NonNull
    private LocalDateTime lastDateToRegister;

    private LocalDateTime postedOn = LocalDateTime.now();

    @DBRef
    private Set<Team> teamsApplied = new HashSet<>();
}
