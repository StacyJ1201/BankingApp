package com.synergisticit.controller;

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
public class DepositController {

    @Autowired
    BankTransactionService bankTransactionService;

    @Autowired
    AccountService accountService;

    @GetMapping("/depositForm")
    public String showDepositForm(Model model, Long accountId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        List<BankTransaction> transactions = bankTransactionService.findTransactionsByUsername(username);
        model.addAttribute("accounts", accountService.findAccountsByAccountHolder(username));
        model.addAttribute("bankTransaction", new BankTransaction());
        model.addAttribute("transactions", bankTransactionService.findTransactionsByUsername(username));
        return "depositForm";
    }

    @PostMapping("/depositMoney")
    public String depositMoney(@Valid @ModelAttribute BankTransaction transaction, BindingResult br, Model model, Authentication auth){
        String username = auth.getName();
        if(br.hasErrors()){
            model.addAttribute("transactions", bankTransactionService.findTransactionsByUsername(username));
            return "depositForm";
        }
        bankTransactionService.deposit(transaction.getAmount(), transaction, transaction.getBankTransactionToAccount());
        return "redirect:/depositForm";
    }
}
