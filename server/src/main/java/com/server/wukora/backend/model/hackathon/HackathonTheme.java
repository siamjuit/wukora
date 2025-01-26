package com.server.wukora.backend.model.hackathon;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "hackathon_themes")
public class HackathonTheme {
    @Id
    private ObjectId Id;
    @Indexed( unique = true )
    private String name;
}
