package com.server.wukora.backend.dto.token;


import com.server.wukora.backend.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {

    private ObjectId id;
    private String token;
    private Instant expiryDate;
    private UserDto user;

}
