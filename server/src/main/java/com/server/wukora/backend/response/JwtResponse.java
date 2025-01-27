package com.server.wukora.backend.response;

import lombok.*;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String token;
}
