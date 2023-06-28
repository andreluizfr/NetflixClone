package com.example.NetflixClone.Business.Accounts;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.CustomExceptions.FailToFindAccountException;
import com.example.NetflixClone.Models.Account;
import com.example.NetflixClone.Repositories.AccountRepositoryDAO;

@Service
public class GetAccountBusiness {

    @Autowired
    AccountRepositoryDAO accountRepository;

    public Account execute(UUID id) throws FailToFindAccountException, IllegalArgumentException, RuntimeException {

        if(id == null) throw new IllegalArgumentException("Error: id is null.");

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent())
            return optionalAccount.get();

        throw new FailToFindAccountException("O id " + id + " não pertence a nenhuma conta registrada.");

    }

}
