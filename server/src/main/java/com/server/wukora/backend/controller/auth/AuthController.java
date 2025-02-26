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
import com.server.wukora.backend.security.user.OAuth2ServiceImpl;
import com.server.wukora.backend.service.token.RefreshTokenService;
import com.server.wukora.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/auth")
public class AuthController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private OAuth2ServiceImpl oAuth2Service;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequest request) {
        try {
            User user = userService.signUp(request);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
            String jwtToken = jwtUtils.generateToken(request.getEmail(), request.getRoles());
            return ResponseEntity.ok(new ApiResponse("Success", new JwtResponse( jwtToken, refreshToken.getToken(), user)));
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

    @GetMapping("/oauth/callback")
    public ResponseEntity<JwtResponse> oauthCallback(@AuthenticationPrincipal OAuth2User oAuth2User) {
        try {
            String email = oAuth2User.getAttribute("email");

            User user = userService.findByEmail(email);
            if (user == null) {
                throw new OAuth2AuthenticationException("User not found");
            }

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getEmail());

            String accessToken = jwtUtils.generateToken(user.getEmail(), user.getRoles());

            JwtResponse response = new JwtResponse(accessToken, refreshToken.getToken(), user);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            // Handle errors if OAuth2 fails
            return ResponseEntity.status(NOT_FOUND).body(null);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenDto refreshToken){
        return refreshTokenService.findByToken(refreshToken.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(userInfo -> {
                    String accessToken = jwtUtils.generateToken(userInfo.getEmail(), userInfo.getRoles());
                    return ResponseEntity.ok(new JwtResponse( accessToken, refreshToken.getToken(), userInfo));
                }).orElseThrow(() -> new ResourceNotFoundException("Refresh Token is not in DB..!!"));
    }
}
