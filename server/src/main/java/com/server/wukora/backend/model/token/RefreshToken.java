package com.server.wukora.backend.model.token;


import com.server.wukora.backend.model.user.User;
import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document( collection = "tokens")
public class RefreshToken {

    @Id
    private ObjectId id;

    private String token;

    private Instant expiryDate;

    @DBRef
    private User user;
}
