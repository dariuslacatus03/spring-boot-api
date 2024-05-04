package com.springbootapi.springbootapi.service;

import com.springbootapi.springbootapi.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account findByUsername(String username);
    List<Account> getAccounts();

}
