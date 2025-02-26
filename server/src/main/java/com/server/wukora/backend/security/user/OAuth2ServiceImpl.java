package com.server.wukora.backend.security.user;

import com.server.wukora.backend.model.token.RefreshToken;
import com.server.wukora.backend.model.user.User;
import com.server.wukora.backend.repository.user.UserRepository;
import com.server.wukora.backend.security.jwt.JwtUtils;
import com.server.wukora.backend.service.token.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        return processOAuth2User(provider, oAuth2User);
    }

    private OAuth2User processOAuth2User(String provider, OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByEmail(email));

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (user.getProvider() == null) {
                throw new OAuth2AuthenticationException(
                        "Email already registered. Please login with password."
                );
            }
            if (!user.getProvider().equals(provider)) {
                throw new OAuth2AuthenticationException(
                        "Email already registered with " + user.getProvider()
                );
            }

            String accessToken = jwtUtils.generateToken(user.getEmail(), user.getRoles());
            String refreshToken = refreshTokenService.createRefreshToken(user.getEmail()).getToken();

            return new OAuth2UserImpl(user, oAuth2User.getAttributes(), accessToken, refreshToken);
        } else {
            User newUser = User.fromOAuth2User(oAuth2User, provider);
            String refreshToken = refreshTokenService.createRefreshToken(newUser.getEmail()).getToken();
            String accessToken = jwtUtils.generateToken(newUser.getEmail(), newUser.getRoles());

            return new OAuth2UserImpl(newUser, oAuth2User.getAttributes(), accessToken, refreshToken);
        }
    }

}
