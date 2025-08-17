package com.forum.forumhub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

    public String gerarToken(UserDetails user) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(algorithm());
    }

    public String extrairUsuario(String token) {
        DecodedJWT jwt = JWT.require(algorithm()).build().verify(token);
        return jwt.getSubject();
    }

    public boolean validarToken(String token) {
        try {
            JWT.require(algorithm()).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
