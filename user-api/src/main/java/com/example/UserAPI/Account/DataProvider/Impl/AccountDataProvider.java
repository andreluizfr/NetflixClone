package com.example.UserAPI.Account.DataProvider.Impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.UserAPI.Account.DataProvider.IAccountDataProvider;
import com.example.UserAPI.Account.Models.Account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class AccountDataProvider implements IAccountDataProvider {

    @PersistenceContext
    private EntityManager manager;

    public Account findById(UUID id) {
        StringBuilder sql = new StringBuilder();
        sql
                .append("SELECT account FROM Account account")
                .append("where account.id = :id");

        TypedQuery<Account> query = manager.createQuery(sql.toString(), Account.class)
                .setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
