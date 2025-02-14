package com.synergisticit.validator;

import com.synergisticit.domain.Account;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Account account = (Account) target;

        if(account.getAccountType() == null){
            errors.rejectValue(
                    "accountType",
                    "account.accountType.empty",
                    "You must choose an account type"
            );
        }

        if (errors.hasFieldErrors("accountBranch")) {
            errors.rejectValue(
                    "accountBranch",
                    "account.accountBranch.invalid",
                    "Please select a valid branch"
            );
        } else if (account.getAccountBranch() == null) {
            errors.rejectValue(
                    "accountBranch",
                    "account.accountBranch.empty",
                    "Please select a branch"
            );
        }
    }
}
