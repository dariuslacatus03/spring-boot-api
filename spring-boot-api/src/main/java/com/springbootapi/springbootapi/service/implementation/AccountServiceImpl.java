package com.springbootapi.springbootapi.service.implementation;

import com.springbootapi.springbootapi.model.Account;
import com.springbootapi.springbootapi.model.Role;
import com.springbootapi.springbootapi.repository.AccountRepository;
import com.springbootapi.springbootapi.repository.RoleRepository;
import com.springbootapi.springbootapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    @Override
    public Account createAccount(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            throw new RuntimeException("ROLE_USER not found in the database");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);
        return accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
