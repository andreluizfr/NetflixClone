package com.example.admin;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JPAUtil{
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

	public static EntityManager getEntityManager() {
		return JPAUtil.emf.createEntityManager();
	}
}