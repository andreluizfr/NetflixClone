package com.example.UserAPI.Account.Business;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.UserAPI.Account.Controller.Models.UpdateAccountPlanDTO;
import com.example.UserAPI.Account.DataProvider.AccountRepository;
import com.example.UserAPI.Account.Exceptions.FailToFindAccountException;
import com.example.UserAPI.Account.Models.Account;

@Service
public class AccountBusiness {

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public Account getAccount(UUID id)
            throws FailToFindAccountException, IllegalArgumentException {

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
                    account.setLimitOfProfiles((short) 2);
                    break;
                case BASIC:
                    account.setLimitOfProfiles((short) 4);
                    break;
                case PREMIUM:
                    account.setLimitOfProfiles((short) 8);
                    break;
            }

        } else
            throw new FailToFindAccountException("Conta " + data.accountId() + " não foi encontrada.");

        Account updatedAccount = accountRepository.save(account);

        return updatedAccount;
    }
}
