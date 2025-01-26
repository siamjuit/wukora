package com.server.wukora.backend.controller.auth;


import com.server.wukora.backend.exception.AlreadyExistsException;
import com.server.wukora.backend.request.SignUpRequest;
import com.server.wukora.backend.response.ApiResponse;
import com.server.wukora.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CONFLICT;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api/prefix}/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    ResponseEntity<ApiResponse> signUp(SignUpRequest request){
        try{

        } catch ( AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }

}
