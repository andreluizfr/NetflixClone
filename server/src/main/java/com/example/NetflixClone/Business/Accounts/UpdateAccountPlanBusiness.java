package com.example.NetflixClone.Business.Accounts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Controllers.Accounts.UpdateAccountPlanDTO;
import com.example.NetflixClone.CustomExceptions.FailToFindAccountException;
import com.example.NetflixClone.Models.Account;
import com.example.NetflixClone.Repositories.AccountRepositoryDAO;

@Service
public class UpdateAccountPlanBusiness {

    @Autowired
    AccountRepositoryDAO accountRepository;

    public Account execute(UpdateAccountPlanDTO data) throws IllegalArgumentException, FailToFindAccountException {

        if(data.accountId() == null | data.plan() == null) throw new IllegalArgumentException("Error: accountId or plan are null");

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

        } else throw new FailToFindAccountException("Conta " + data.accountId() + " não foi encontrada.");

        Account updatedAccount = accountRepository.save(account);
        
        return updatedAccount;

    }

}
