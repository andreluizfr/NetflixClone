package com.example.UserService.Audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.UserService.User.Models.User;

import jakarta.servlet.http.HttpServletRequest;

public class AuditListener implements RevisionListener { 

	@Override
    public void newRevision(Object revisionEntity) {    	
    	AuditEntity revEntity = (AuditEntity) revisionEntity;  

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            User user = (User) authentication.getPrincipal();
            revEntity.setUsername(user.getUsername());
        }

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String ip = request.getRemoteAddr();
            revEntity.setIp(ip);
        }
    }
}