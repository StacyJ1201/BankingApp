package com.synergisticit.controller;

import com.synergisticit.domain.Account;
import com.synergisticit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> findAllAccounts(){
        List<Account> accounts = accountService.findAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account role){
        Account createdAccount = accountService.saveAccount(role);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> findAccountById(@PathVariable Long accountId){
        Optional<Account> optAccount = accountService.findAccountById(accountId);
        Account foundAccount = optAccount.get();
        return ResponseEntity.ok(foundAccount);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, Account account){
        Optional<Account> optAccount = accountService.findAccountById(accountId);
        Account foundAccount = optAccount.get();

        foundAccount.setAccountId(account.getAccountId());
        foundAccount.setAccountBalance(account.getAccountBalance());
        foundAccount.setAccountCustomer(account.getAccountCustomer());
        foundAccount.setAccountDateOpened(account.getAccountDateOpened());
        foundAccount.setAccountHolder(account.getAccountHolder());
        foundAccount.setAccountType(account.getAccountType());
        foundAccount.setAccountBranch(account.getAccountBranch());

        Account updateAccount = accountService.saveAccount(foundAccount);

        return ResponseEntity.ok(updateAccount);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccountById(accountId);
        return ResponseEntity.noContent().build();
    }
}
