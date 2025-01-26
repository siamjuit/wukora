package com.server.wukora.backend.service.user;

import com.server.wukora.backend.exception.AlreadyExistsException;
import com.server.wukora.backend.exception.ResourceNotFoundException;
import com.server.wukora.backend.model.user.User;
import com.server.wukora.backend.repository.user.UserRepository;
import com.server.wukora.backend.request.SignUpRequest;
import com.server.wukora.backend.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(ObjectId id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User signUp(SignUpRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail( request.getEmail()))
                .map( req -> {
                        User user = new User();
                        user.setName(req.getName());
                        user.setEmail(req.getEmail());
                        user.setPassword(passwordEncoder.encode(req.getPassword()));
                        user.setSkills(req.getSkills());
                        return userRepository.save(user);
                    }
                ).orElseThrow(() -> new AlreadyExistsException("User with email: " + request.getEmail() + " already exists, please try again!!!"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, ObjectId id) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setSkills(request.getSkills());
            existingUser.setName(request.getName());
            existingUser.setPassword(request.getPassword());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(ObjectId id) {
        userRepository.findById(id).ifPresentOrElse(userRepository :: delete, () -> {
            throw new ResourceNotFoundException("User not found");
        });
    }
}
