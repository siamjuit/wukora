package com.server.wukora.backend.response;

import lombok.*;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;
}
