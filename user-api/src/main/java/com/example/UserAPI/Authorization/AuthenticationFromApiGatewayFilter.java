package com.example.UserAPI.Authorization;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.UserAPI.Authorization.DataProvider.RoleRepository;
import com.example.UserAPI.Authorization.Models.CustomPermission;
import com.example.UserAPI.Authorization.Models.Permission;
import com.example.UserAPI.Authorization.Models.Role;
import com.example.UserAPI.User.DataProvider.UserRepository;
import com.example.UserAPI.User.Models.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFromApiGatewayFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Autowired 
    RoleRepository roleRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String email = request.getHeader("X-Logged-In-User");
        
        if(email != null) {
            UserDetails userDetailed = userRepository.findByEmail(email);

            if(userDetailed != null){
                User user = (User) userDetailed;
                Optional<Role> optionalRole = roleRepository.findById(user.getRole());

                Set<Permission> permissionsByRole = new HashSet<>();
                if(optionalRole.isPresent()){
                    permissionsByRole = new HashSet<>(optionalRole.get().getPermissions());
                }

                List<CustomPermission> permissionsNamesByRole = permissionsByRole
                    .stream()
                    .map(permission -> new CustomPermission(permission.getName()))
                    .collect(Collectors.toList());

                user.setPermissionsNamesByRole(permissionsNamesByRole);

                var authentication = new UsernamePasswordAuthenticationToken(userDetailed, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
                
            } else {
                throw new UsernameNotFoundException("User not found in BD");
            }
        }

        filterChain.doFilter(request, response);
    }

}
