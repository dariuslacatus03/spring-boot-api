package com.springbootapi.springbootapi.resource;

import com.springbootapi.springbootapi.model.Account;
import com.springbootapi.springbootapi.model.Employee;
import com.springbootapi.springbootapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springbootapi.springbootapi.resource.EmployeeResource.getLocation;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/accounts")
public class AccountResource {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account)
    {
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.created(getLocation(newAccount.getId().intValue())).body(newAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts()
    {
        return ResponseEntity.ok(accountService.getAccounts());
    }

}
