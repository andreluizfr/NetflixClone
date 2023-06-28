package com.example.NetflixClone.Business.Accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Models.Account;
import com.example.NetflixClone.Repositories.AccountRepositoryDAO;

@Service
public class GetAllAccountsBusiness {

    @Autowired
    AccountRepositoryDAO accountRepository;

    public List<Account> execute() {

        List<Account> accounts = accountRepository.findAll();

        return accounts;

    }

}
