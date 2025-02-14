package com.synergisticit.validator;

import com.synergisticit.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "username",
                "user.username.empty",
                "Username must not be empty"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "password",
                "user.password.empty",
                "Password must not be empty"
        );

        if(user.getPassword() != null && user.getPassword().length() < 8){
            errors.rejectValue(
                    "password",
                    "user.password.tooShort",
                    "Password must be at least 8 characters long"
            );
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "email",
                "user.email.empty",
                "Email must not be empty"
        );

        if(user.getRoles() == null || user.getRoles().isEmpty()){
            errors.rejectValue(
                    "roles",
                    "user.roles.isEmpty",
                    "User must have at least one role"
            );
        }

    }
}
