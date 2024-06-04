package com.example.MediaAPI.Util;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenUtils {

	@Value("${api.security.token.jwtSecret}")
	private String jwtSecret;

	public String getEmailFromToken(String token) throws JWTVerificationException {

		Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

		return JWT.require(algorithm)
				.withIssuer("auth-api")
				.build()
				.verify(token)
				.getSubject();
	}

	public List<String> getPermissionsFromToken(String token) throws JWTVerificationException {

		Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

		return List.of(JWT.require(algorithm)
				.withIssuer("auth-api")
				.build()
				.verify(token)
				.getClaim("permissions").asArray(String.class));
	}

	public String recoverToken(HttpServletRequest request) {

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null)
			return null;

		return authHeader.replace("Bearer ", "");
	}

}
