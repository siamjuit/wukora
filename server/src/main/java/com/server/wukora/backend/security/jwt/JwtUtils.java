package com.server.wukora.backend.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtUtils {

    private String secretKey = "36763979244226452948404D635166546A576D5A7134743777217A25432A462D";

    private int expirationTime = 360000000;

    public String generateToken(String username, List<String> roles){
        return Jwts.builder()
                .claims()
                .add("username", username)
                .add("roles", roles)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + expirationTime))
                .and()
                .signWith(key())
                .compact();
    }

    private SecretKey key(){
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }


    public String getUsernameFromToken(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function< Claims, T > claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        try{
            final String username = getUsernameFromToken(token);
            return ( username.equals(userDetails.getUsername()) && !isTokenExpired(token) );
        } catch (Exception e){
            throw new JwtException(e.getMessage());
        }
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
}
