package com.aduilio.med.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aduilio.med.entity.User;
import com.aduilio.med.exception.InvalidAuthorizationTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

/**
 * Provides token services.
 */
@Service
public class TokenService {

    private static final String ISSUER = "MedAPI";

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Generates a JWT Token.
     * 
     * @param user the user that is requesting the token
     * @return String
     */
    public String generate(User user) {
        try {
            return JWT.create().withIssuer(ISSUER).withSubject(user.getUsername()).withExpiresAt(getExpireDate())
                    .sign(algorithm());
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Fail to generate the token", exception);
        }
    }

    /**
     * Validates a JWT Token and return the user.
     * 
     * @param token the token to validate
     * @return String
     */
    public String validate(String token) {
        try {
            return JWT.require(algorithm()).withIssuer(ISSUER).build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new InvalidAuthorizationTokenException();
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Instant getExpireDate() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC);
    }
}
