package com.synergisticit.service;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.BankTransactionType;
import com.synergisticit.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BankTransactionServiceImpl implements BankTransactionService{

    @Autowired
    AccountService accountService;

    @Autowired
    BankTransactionRepository bankTransactionRepository;


    @Override
    public BankTransaction saveTransaction(BankTransaction bankTransaction) {
        return bankTransactionRepository.save(bankTransaction);
    }

    @Override
    public void deposit(double depositAmount, BankTransaction transaction, Long accountId){

        if(depositAmount < 0){
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }

        Account depositedAccount = accountService.findAccountById(accountId).get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        transaction.setBankTransactionDateTime(LocalDateTime.now());
        transaction.setBankTransactionType(BankTransactionType.DEPOSIT);
        transaction.setAmount(depositAmount);
        transaction.setBankTransactionToAccount(accountId);
        transaction.setUsername(username);

        double newAmount = depositedAccount.getAccountBalance() + depositAmount;
        depositedAccount.setAccountBalance(newAmount);

        accountService.updateAccount(accountId, depositedAccount);
        bankTransactionRepository.save(transaction);
    }

    @Override
    public void withdraw(double withdrawalAmount, Long accountId, BankTransaction transaction) {

        if(withdrawalAmount < 0){
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Account withdrewAccount = accountService.findAccountById(accountId).get();

        transaction.setBankTransactionDateTime(LocalDateTime.now());
        transaction.setBankTransactionType(BankTransactionType.WITHDRAW);
        transaction.setAmount(withdrawalAmount);
        transaction.setBankTransactionToAccount(accountId);
        transaction.setUsername(username);

        double newAmount = withdrewAccount.getAccountBalance() - withdrawalAmount;
        withdrewAccount.setAccountBalance(newAmount);

        accountService.updateAccount(accountId, withdrewAccount);
        bankTransactionRepository.save(transaction);

    }

    @Override
    public void transfer(double transferAmount, Long fromAccountId, Long toAccountId, BankTransaction transaction) {

        if(transferAmount < 0){
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Account fromAccount = accountService.findAccountById(fromAccountId).get();
        Account toAccount = accountService.findAccountById(toAccountId).get();

        transaction.setBankTransactionDateTime(LocalDateTime.now());
        transaction.setBankTransactionType(BankTransactionType.TRANSFER);
        transaction.setAmount(transferAmount);
        transaction.setBankTransactionFromAccount(fromAccountId);
        transaction.setBankTransactionToAccount(toAccountId);
        transaction.setUsername(username);

        double newFromAmount = fromAccount.getAccountBalance() - transferAmount;
        double newToAmount = toAccount.getAccountBalance() + transferAmount;

        fromAccount.setAccountBalance(newFromAmount);
        toAccount.setAccountBalance(newToAmount);


        accountService.updateAccount(fromAccountId, fromAccount);
        accountService.updateAccount(toAccountId, toAccount);
        bankTransactionRepository.save(transaction);

    }

    @Override
    public List<BankTransaction> allTransactions() {
        return bankTransactionRepository.findAll();
    }

    @Override
    public List<BankTransaction> findTransactionsByUsername(String username) {
        return bankTransactionRepository.findBankTransactionsByUsername(username);
    }
}
