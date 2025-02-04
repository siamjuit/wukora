package com.server.wukora.backend.service.token;


import com.server.wukora.backend.exception.ResourceNotFoundException;
import com.server.wukora.backend.model.token.RefreshToken;
import com.server.wukora.backend.model.user.User;
import com.server.wukora.backend.repository.token.RefreshTokenRepository;
import com.server.wukora.backend.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshToken createRefreshToken(String email){
        User user = userRepository.findByEmail(email);
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if( token.getExpiryDate().compareTo(Instant.now()) < 0 ){
            refreshTokenRepository.delete(token);
            throw new ResourceNotFoundException("Session expired, please login again");
        }
        return token;
    }

    public Optional<RefreshToken> findByToken(String token){
        return Optional.of(refreshTokenRepository.findByToken(token)).orElseThrow(() -> new ResourceNotFoundException(("User not found")));
    }


}
