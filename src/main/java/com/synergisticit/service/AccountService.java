package com.synergisticit.service;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Customer;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account saveAccount(Account account);
    Optional<Account> findAccountById(long accountId);
    List<Account> findAllAccounts();
    Account updateAccount(long accountId, Account account);
    void deleteAccountById(long id);
    List<Account> findAccountsByAccountHolder(String username);
}
