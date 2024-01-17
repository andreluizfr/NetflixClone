package com.example.ApiGateway.Authentication.Business;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ApiGateway.Authentication.DataProvider.RoleRepository;
import com.example.ApiGateway.Authentication.Models.Permission;
import com.example.ApiGateway.Authentication.Models.Role;
import com.example.ApiGateway.Authentication.Models.User;

@Service
public class TokenService {

    @Value("${api.security.token.jwtSecret}")
    private String jwtSecret;

    @Value("${api.security.token.jwtExpirationTime}")
    private String jwtExpirationTime;

    @Autowired
    RoleRepository roleRepository;

    public String generateToken(User user) throws JWTCreationException {

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        this.buildPermissionsByRole(user);

        String token = JWT.create()
                .withIssuer("auth-api")
                .withSubject(user.getEmail())
                .withArrayClaim("permissions", user.getAuthorities().toArray(String[]::new))
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

    private void buildPermissionsByRole(User user) {

        Optional<Role> optionalRole = roleRepository.findById(user.getRole());

        Set<Permission> permissionsByRole = new HashSet<>();
        if (optionalRole.isPresent()) {
            permissionsByRole = new HashSet<>(optionalRole.get().getPermissions());

            user.setPermissionsByRole(permissionsByRole
                    .stream()
                    .map(Permission::getName)
                    .collect(Collectors.toSet()));
        }
    }
}
