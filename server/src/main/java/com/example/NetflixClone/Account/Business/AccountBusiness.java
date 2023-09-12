package com.example.NetflixClone.Account.Business;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.NetflixClone.Account.Controller.Models.UpdateAccountPlanDTO;
import com.example.NetflixClone.Account.DataProvider.AccountRepository;
import com.example.NetflixClone.Account.Models.Account;
import com.example.NetflixClone.Exceptions.FailToFindAccountException;

@Service
public class AccountBusiness {

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public Account getAccount(UUID id) throws FailToFindAccountException, IllegalArgumentException, RuntimeException {

        if (id == null)
            throw new IllegalArgumentException("Error: id is null.");

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent())
            return optionalAccount.get();

        throw new FailToFindAccountException("O id " + id + " não pertence a nenhuma conta registrada.");
    }

    @Transactional
    public List<Account> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();

        return accounts;
    }

    @Transactional
    public Account updateAccountPlan(UpdateAccountPlanDTO data)
            throws IllegalArgumentException, FailToFindAccountException {

        if (data.accountId() == null | data.plan() == null)
            throw new IllegalArgumentException("Error: accountId or plan are null");

        Optional<Account> optionalAccount = accountRepository.findById(data.accountId());

        Account account;
        if (optionalAccount.isPresent()) {

            account = optionalAccount.get();
            account.setCurrentPlan(data.plan());

            switch (data.plan()) {
                case BASIC_WITH_ADS:
                    account.setLimitOfProfiles(2);
                    break;
                case BASIC:
                    account.setLimitOfProfiles(4);
                    break;
                case PREMIUM:
                    account.setLimitOfProfiles(8);
                    break;
            }

        } else
            throw new FailToFindAccountException("Conta " + data.accountId() + " não foi encontrada.");

        Account updatedAccount = accountRepository.save(account);

        return updatedAccount;
    }
}
