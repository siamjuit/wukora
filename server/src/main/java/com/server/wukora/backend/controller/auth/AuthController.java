package com.server.wukora.backend.controller.auth;


import com.server.wukora.backend.dto.user.UserDto;
import com.server.wukora.backend.exception.AlreadyExistsException;
import com.server.wukora.backend.exception.ResourceNotFoundException;
import com.server.wukora.backend.model.user.User;
import com.server.wukora.backend.request.LogInRequest;
import com.server.wukora.backend.request.SignUpRequest;
import com.server.wukora.backend.response.ApiResponse;
import com.server.wukora.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api/prefix}/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequest request){
        try{
            User user = userService.signUp(request);
            UserDto userDto = userService.convertToDto(user);
            return ResponseEntity.ok(new ApiResponse("Success", userDto));
        } catch ( AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse> login(@RequestBody LogInRequest request){
        try{

        } catch( ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }



}
