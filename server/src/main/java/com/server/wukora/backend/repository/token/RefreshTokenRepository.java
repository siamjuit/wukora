package com.server.wukora.backend.repository.token;

import com.server.wukora.backend.model.token.RefreshToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, ObjectId> {
    Optional<RefreshToken> findByToken(String token);
}
