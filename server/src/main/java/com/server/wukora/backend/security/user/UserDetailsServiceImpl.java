package com.server.wukora.backend.security.user;


import com.server.wukora.backend.exception.ResourceNotFoundException;
import com.server.wukora.backend.model.user.User;
import com.server.wukora.backend.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByEmail(username))
                .orElseThrow( () -> new ResourceNotFoundException("User with email" + username + "not found"));

        return UserDetailsImpl.buildUserDetails(user);
    }
}
