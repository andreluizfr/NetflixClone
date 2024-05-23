package com.example.UserAPI.Account.Business;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.UserAPI.Account.Controller.Models.UpdateAccountPlanDTO;
import com.example.UserAPI.Account.DataProvider.AccountRepository;
import com.example.UserAPI.Account.Exceptions.FailToFindAccountException;
import com.example.UserAPI.Account.Models.Account;
import com.google.gson.Gson;

@Transactional
@Service
public class AccountBusiness {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Gson gson;

    public Account getAccount(UUID id)
            throws FailToFindAccountException, IllegalArgumentException {

        if (id == null)
            throw new IllegalArgumentException("Error: id is null.");

        return accountRepository
                .findById(id)
                .orElseThrow(() -> new FailToFindAccountException("O id " + id + " não pertence a nenhuma conta registrada."));
    }

    @SuppressWarnings("unchecked")
    public List<Account> getAllAccounts() {
        return gson.fromJson(getAllAccountsCached(), List.class);
    }

    @Cacheable(cacheNames = "smallTimeCache")
    public String getAllAccountsCached() {
        List<Account> accounts = accountRepository.findAll();

        Gson gson = new Gson();
        return gson.toJson(accounts);
    }

    public Account updateAccountPlan(UpdateAccountPlanDTO data)
            throws IllegalArgumentException, FailToFindAccountException {

        if (data.getAccountId() == null | data.getPlan() == null)
            throw new IllegalArgumentException("Error: accountId or plan are null");
        
        Account account = accountRepository
                .findById(data.getAccountId())
                .orElseThrow(() ->  new FailToFindAccountException("Conta " + data.getAccountId() + " não foi encontrada."));

        account.setCurrentPlan(data.getPlan());
        updateLimitOfProfilesBasedOnPlan(account);

        Account updatedAccount = accountRepository.save(account);

        return updatedAccount;
    }

    private void updateLimitOfProfilesBasedOnPlan(Account account) {
        switch (account.getCurrentPlan()) {
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
    }
}
