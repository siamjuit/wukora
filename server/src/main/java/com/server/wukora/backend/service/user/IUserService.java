package com.server.wukora.backend.service.user;

import com.server.wukora.backend.dto.user.UserDto;
import com.server.wukora.backend.model.user.User;
import com.server.wukora.backend.request.SignUpRequest;
import com.server.wukora.backend.request.UpdateUserRequest;
import org.bson.types.ObjectId;

public interface IUserService {
    User getUserById(ObjectId id);
    User signUp(SignUpRequest request);
    User updateUser(UpdateUserRequest request , ObjectId id);
    void deleteUser( ObjectId id );
    UserDto convertToDto(User user);
    UserDto login(String email);
    User findByEmail(String email);
}
