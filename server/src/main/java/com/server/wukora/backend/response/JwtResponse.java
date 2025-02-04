package com.server.wukora.backend.response;

import com.server.wukora.backend.model.user.User;
import lombok.*;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private User user;
}
