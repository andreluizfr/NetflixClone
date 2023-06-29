package com.example.NetflixClone.Business.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class AuthenticationService implements UserDetailsService{

    @Autowired
    UserRepositoryDAO userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
    
}
