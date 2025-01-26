package com.server.wukora.backend.request;


import lombok.Data;

@Data
public class LogInRequest {
    private String email;
    private String password;
}
