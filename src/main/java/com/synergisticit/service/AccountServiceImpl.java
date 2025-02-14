package com.synergisticit.service;


import com.synergisticit.domain.Account;
import com.synergisticit.domain.Branch;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import com.synergisticit.repository.AccountRepository;
import com.synergisticit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Account saveAccount(Account account) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        account.setAccountHolder(username);
        Optional<User> foundUser = userRepository.findUserByUsername(username);
        User user = foundUser.get();
        Customer customer = user.getCustomer();

        account.setAccountCustomer(customer);
        account.setAccountBalance(0.0);
        account.setAccountDateOpened(LocalDate.now());
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountById(long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(long accountId, Account account) {
        Optional<Account> foundAccount = findAccountById(accountId);
        Account updatedAccount = foundAccount.get();

        updatedAccount.setAccountBalance(account.getAccountBalance());
        updatedAccount.setAccountBranch(account.getAccountBranch());
        updatedAccount.setAccountCustomer(account.getAccountCustomer());
        updatedAccount.setAccountDateOpened(account.getAccountDateOpened());
        updatedAccount.setAccountHolder(account.getAccountHolder());
        updatedAccount.setAccountType(account.getAccountType());

        return accountRepository.save(updatedAccount);
    }

    @Override
    public void deleteAccountById(long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> findAccountsByAccountHolder(String username) {
        return accountRepository.findAccountsByAccountHolder(username);
    }


}
