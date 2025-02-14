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
public class TransferController {

    @Autowired
    BankTransactionService bankTransactionService;

    @Autowired
    AccountService accountService;

    @GetMapping("/transferForm")
    public String showTransferForm(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("accounts", accountService.findAccountsByAccountHolder(username));
        model.addAttribute("transaction", new BankTransaction());
        model.addAttribute("transactions", bankTransactionService.findTransactionsByUsername(username));
        return "transferForm";
    }

    @PostMapping("/transferMoney")
    public String transferMoney(@Valid @ModelAttribute BankTransaction transaction, BindingResult br, Model model, Authentication auth){
        String username = auth.getName();
        if(br.hasErrors()){
            model.addAttribute("transactions", bankTransactionService.findTransactionsByUsername(username));
            return "transferForm";
        }
        bankTransactionService.transfer(transaction.getAmount(), transaction.getBankTransactionFromAccount(),
                transaction.getBankTransactionToAccount(), transaction);
        return "redirect:/transferForm";
    }
}
