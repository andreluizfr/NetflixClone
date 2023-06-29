package com.example.NetflixClone.Business.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Models.User;
import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class FetchUserBusiness {

    @Autowired
    UserRepositoryDAO userRepository;

    public User execute() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {

            User user = (User) authentication.getPrincipal();

            return user;
        }

        throw new RuntimeException("Error: Nenhum usuário válido autenticado encontrado no contexto");

    }
}
