package com.server.wukora.backend.model.user;

import com.server.wukora.backend.model.skill.Skill;
import com.server.wukora.backend.model.team.Team;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;
    @Indexed( unique = true )
    @NonNull
    private String email;

    //Password can be null for oauth users
    private String password;
    @NonNull
    private String name;
    private List<String> roles = new ArrayList<>(List.of("ROLE_USER"));

    // OAuth2 fields
    private String provider;
    private String providerId;

    @DBRef
    private Set<Skill> skills = new HashSet<>();
    @DBRef
    private Set<Team> appliedTeams = new HashSet<>();
    private String gitHubUrl = "";
    private String portfolioUrl = "";
    private String bio = "";
    private boolean lookingForTeam = false;
    private String profileUrl = "";
    private String bannerUrl = "";

    public static User fromOAuth2User(OAuth2User oAuth2User, String provider) {
        User user = new User();
        user.setProvider(provider);
        user.setProviderId(oAuth2User.getName());
        user.setEmail(Objects.requireNonNull(oAuth2User.getAttribute("email")));
        user.setName(Objects.requireNonNull(oAuth2User.getAttribute("name")));

        if ("github".equalsIgnoreCase(provider)) {
            user.setProfileUrl(oAuth2User.getAttribute("avatar_url"));
            user.setGitHubUrl(oAuth2User.getAttribute("html_url"));
        } else if ("google".equalsIgnoreCase(provider)) {
            user.setProfileUrl(oAuth2User.getAttribute("picture"));
        }

        return user;
    }
}
