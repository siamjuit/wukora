package com.server.wukora.backend.security.user;

import com.server.wukora.backend.model.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class OAuth2UserImpl implements OAuth2User {

    @Getter
    private final User user;
    private final Map<String, Object> attributes;
    @Getter
    private final String accessToken;
    @Getter
    private final String refreshToken;

    public Map<String, Object> getAttributes(String key) {
        return attributes;
    }

    @Override
    public <A> A getAttribute(String name) {
        return (A) attributes.get(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return user.getRoles().stream()
               .map(SimpleGrantedAuthority::new)
               .collect(Collectors.toList());
   }

    @Override
    public String getName() {
        return user.getEmail();
    }

}
