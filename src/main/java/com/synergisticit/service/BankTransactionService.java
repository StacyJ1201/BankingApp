package com.synergisticit.service;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface BankTransactionService {
    BankTransaction saveTransaction(BankTransaction bankTransaction);
    List<BankTransaction> allTransactions();
    List<BankTransaction> findTransactionsByUsername(String username);
    void deposit(double depositAmount, BankTransaction transaction, Long accountId);
    void withdraw(double withdrawalAmount, Long accountId, BankTransaction transaction);
    void transfer(double transferAmount, Long fromAccountId, Long toAccountId, BankTransaction transaction);
}
