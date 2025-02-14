package com.synergisticit.validator;

import com.synergisticit.domain.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Role.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Role role = (Role) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "roleName",
                "role.roleName.empty",
                "Role Name is required"
        );

        if(role.getRoleName() != null && role.getRoleName().length() < 2){
            errors.rejectValue(
                    "roleName",
                    "role.roleName.tooShort",
                    "Role must be two characters long"
            );
        }
    }
}
