package com.server.wukora.backend.repository.user;

import com.server.wukora.backend.model.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);
}
