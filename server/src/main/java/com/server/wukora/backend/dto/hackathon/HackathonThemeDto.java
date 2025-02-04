package com.server.wukora.backend.dto.hackathon;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class HackathonThemeDto {
    private ObjectId id;
    private String name;
}
