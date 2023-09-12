package com.example.NetflixClone.Account.DataProvider;

import java.util.UUID;

import com.example.NetflixClone.Account.Models.Account;

public interface IAccountDataProvider {
    public Account findById(UUID id);
}
