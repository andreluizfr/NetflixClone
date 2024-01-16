package com.example.UserAPI.Account.DataProvider;

import java.util.UUID;

import com.example.UserAPI.Account.Models.Account;

public interface IAccountDataProvider {
    public Account findById(UUID id);
}
