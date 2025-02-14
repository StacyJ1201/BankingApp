package com.synergisticit.controller;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.User;
import com.synergisticit.repository.AccountRepository;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BranchService;
import com.synergisticit.validator.AccountValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BranchService branchService;

    @Autowired
    AccountValidator accountValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(accountValidator);
    }

    @GetMapping("/accountForm")
    public String showAccountForm(Model model, Principal principal){
        Account account = new Account();
        if(accountRepository.findMaxAccountId() != null){
            account.setAccountId(accountRepository.findMaxAccountId() + 1);
        } else {
            account.setAccountId(1L);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isManager = auth.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_Admin"));
        List<Account> accounts;
        if(isManager){
            accounts  =accountService.findAllAccounts();
        } else {
            accounts = accountService.findAccountsByAccountHolder(principal.getName());
        }
        model.addAttribute("account", account);
        model.addAttribute("accounts", accounts);
        model.addAttribute("branches", branchService.findAllBranches());
        return "accountForm";
    }

    @PostMapping("/saveAccount")
    public String saveAccount(@Valid @ModelAttribute("account") Account account, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("branches", branchService.findAllBranches());
            model.addAttribute("accounts", accountService.findAllAccounts());
            return "accountForm";
        }
        accountService.saveAccount(account);
        return "redirect:/accountForm";
    }

    @GetMapping("/updateAccount/{id}")
    public String updateAccount(@PathVariable Long id, Model model){
        Optional<Account> foundAccount = accountService.findAccountById(id);
        if(foundAccount.isPresent()){
            model.addAttribute("account", foundAccount.get());
            model.addAttribute("accounts", accountService.findAllAccounts());
            return "accountForm";
        } else {
            return "accountForm";
        }
    }

    @GetMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable Long id){
        accountService.deleteAccountById(id);
        return "redirect:/accountForm";
    }
}
