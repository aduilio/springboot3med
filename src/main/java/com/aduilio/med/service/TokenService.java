package com.aduilio.med.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aduilio.med.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

/**
 * Provides token services.
 */
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generate(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("MedAPI").withSubject(user.getUsername()).withExpiresAt(getExpireDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Fail to generate the token", exception);
        }
    }

    private Instant getExpireDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
    }
}
