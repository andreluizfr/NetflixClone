package com.example.MediaService.Authorization;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.MediaService.Authorization.DataProvider.PermissionRepository;
import com.example.MediaService.Authorization.DataProvider.UserRepository;
import com.example.MediaService.Authorization.Models.Permission;
import com.example.MediaService.Authorization.Models.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HeaderSecurityFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String email = request.getHeader("X-Logged-In-User");
        
        if(email != null) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            User user = null;

            if(optionalUser.isPresent()){
                user = optionalUser.get();

                List<Permission> permissionsByRole = permissionRepository.findByRolesIn(user.getRole());

                List<CustomPermission> permissionsNamesByRole = permissionsByRole
                    .stream()
                    .map(permission -> new CustomPermission(permission.getName()))
                    .collect(Collectors.toList());

                user.setPermissionsNamesByRole(permissionsNamesByRole);

                var authentication = new UsernamePasswordAuthenticationToken((UserDetails) user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}

