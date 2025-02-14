package com.synergisticit.controller;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WithdrawController {

    @Autowired
    AccountService accountService;

    @Autowired
    BankTransactionService transactionService;

    @GetMapping("/withdrawalForm")
    public String showWithdrawalForm(Model model ){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        List<Account> accounts = accountService.findAccountsByAccountHolder(username);
        model.addAttribute("accounts", accounts);
        model.addAttribute("transaction", new BankTransaction());
        model.addAttribute("transactions", transactionService.findTransactionsByUsername(username));

        return "withdrawalForm";

    }

    @PostMapping("/withdrawMoney")
    public String withdrawMoney(@Valid @ModelAttribute BankTransaction transaction, BindingResult br, Model model, Authentication auth){

        if(br.hasErrors()){
            String username = auth.getName();
            model.addAttribute("transactions", transactionService.findTransactionsByUsername(username));
            return "withdrawalForm";
        }
        transactionService.withdraw(transaction.getAmount(), transaction.getBankTransactionFromAccount(), transaction);
        return"redirect:/withdrawalForm";
    }
}
