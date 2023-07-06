package com.example.admin;

import javax.faces.bean.ManagedBean;

import org.flywaydb.core.Flyway;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;

@ManagedBean
@Singleton
@Startup
public class MyStartupBean {
	
	@PostConstruct
    public void init() {
        System.out.println("Aplicação JSF iniciada. Executando código de inicialização...");
        
        EntityManager em = JPAUtil.getEntityManager();

        String url = em.getProperties().get("jakarta.persistence.jdbc.url").toString();
        String username = em.getProperties().get("jakarta.persistence.jdbc.user").toString();
        String password = em.getProperties().get("jakarta.persistence.jdbc.password").toString();

        Flyway flyway = Flyway.configure()
                .dataSource(url, username, password)
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();

        em.close();
    }

}