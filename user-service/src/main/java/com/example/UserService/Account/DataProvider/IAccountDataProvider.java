package com.example.UserService.Account.DataProvider;

import java.util.UUID;

import com.example.UserService.Account.Models.Account;

public interface IAccountDataProvider {
    public Account findById(UUID id);
}
