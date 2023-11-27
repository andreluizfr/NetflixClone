package com.example.ApiGateway.Authentication;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ApiGateway.Authentication.Models.User;

@Service
public class TokenService {

    @Value("${api.security.token.jwtSecret}")
    private String jwtSecret;

    @Value("${api.security.token.jwtExpirationTime}")
    private String jwtExpirationTime;

    public String generateToken(User user) throws JWTCreationException {

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        String token = JWT.create()
                .withIssuer("auth-api")
                .withSubject(user.getEmail())
                .withExpiresAt(this.genExpirationDate())
                .sign(algorithm);

        return token;
    }

    public String validateToken(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .getSubject();
    }

    private Date genExpirationDate() {
        return new Date((new Date()).getTime() + (Integer.parseInt(jwtExpirationTime)));
    }
}
