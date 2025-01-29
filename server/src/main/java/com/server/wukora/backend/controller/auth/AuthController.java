package com.server.wukora.backend.controller.auth;


import com.server.wukora.backend.dto.token.RefreshTokenDto;
import com.server.wukora.backend.dto.user.UserDto;
import com.server.wukora.backend.exception.AlreadyExistsException;
import com.server.wukora.backend.exception.ResourceNotFoundException;
import com.server.wukora.backend.model.token.RefreshToken;
import com.server.wukora.backend.model.user.User;
import com.server.wukora.backend.request.LogInRequest;
import com.server.wukora.backend.request.SignUpRequest;
import com.server.wukora.backend.response.ApiResponse;
import com.server.wukora.backend.response.JwtResponse;
import com.server.wukora.backend.security.jwt.JwtUtils;
import com.server.wukora.backend.service.token.RefreshTokenService;
import com.server.wukora.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/auth")
public class AuthController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequest request) {
        try {
            User user = userService.signUp(request);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
            String jwtToken = java.lang.String.valueOf(jwtUtils.generateToken(request.getEmail(), request.getRoles()));
            return ResponseEntity.ok(new ApiResponse("Success", new JwtResponse( jwtToken, refreshToken.getToken())));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Failure: " + e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LogInRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDto userDto = userService.login(request.getEmail());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
            return ResponseEntity.ok(new ApiResponse("Success", userDto));
        } else {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failure: " + "Session not found", null));
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenDto refreshToken){
        return refreshTokenService.findByToken(refreshToken.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(userInfo -> {
                    String accessToken = jwtUtils.generateToken(userInfo.getEmail(), userInfo.getRoles());
                    return ResponseEntity.ok(new JwtResponse( accessToken, refreshToken.getToken() ));
                }).orElseThrow(() -> new ResourceNotFoundException("Refresh Token is not in DB..!!"));
    }
}
