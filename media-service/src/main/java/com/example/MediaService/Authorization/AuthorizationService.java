package com.example.MediaService.Authorization;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MediaService.Authorization.DataProvider.PermissionRepository;
import com.example.MediaService.Authorization.DataProvider.UserRepository;
import com.example.MediaService.Authorization.Models.Permission;
import com.example.MediaService.Authorization.Models.User;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            List<Permission> permissionsByRole = permissionRepository.findByRolesIn(user.getRole());

            List<CustomPermission> permissionsNamesByRole = permissionsByRole
                .stream()
                .map(permission -> new CustomPermission(permission.getName()))
                .collect(Collectors.toList());

            user.setPermissionsNamesByRole(permissionsNamesByRole);
            return (UserDetails) user;
        }
        
        throw new UsernameNotFoundException("User not found in BD");
    }
}