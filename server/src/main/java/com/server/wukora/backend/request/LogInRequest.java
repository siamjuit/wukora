package com.server.wukora.backend.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequest {
    private String email;
    private String password;
}
