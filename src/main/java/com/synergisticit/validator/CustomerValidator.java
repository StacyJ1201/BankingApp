package com.synergisticit.validator;

import com.synergisticit.domain.Customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class CustomerValidator implements Validator {

    private static final LocalDate isOldEnough = LocalDate.now().minusYears(18);

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "customerName",
                "customer.customerName.empty",
                "Customer Name cannot be empty"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "customerGender",
                "customer.customerGender.empty",
                "Gender cannot be empty"
        );

        if(customer.getCustomerDOB() == null){
            errors.rejectValue(
                    "customerDOB",
                    "customer.customerDOB.empty",
                    "Customer DOB must not be empty"
            );
        }
        else if(customer.getCustomerDOB().isAfter(isOldEnough)){
            errors.rejectValue(
                    "customerDOB",
                    "customer.customerDOB.tooYoung",
                    "Customer must be 18 years or older"
            );
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "customerSSN",
                "customer.customerSSN.empty",
                "SSN cannot be empty"
        );

        if(customer.getCustomerSSN() != null && customer.getCustomerSSN().length() != 9){
            errors.rejectValue(
                    "customerSSN",
                    "customer.customerSSN.invalidLength",
                    "total amount of numbers in your SSN must be 9"
            );
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "customerAddress.addressLine1",
                "customer.customerAddress.addressLine1.empty",
                "Address line 1 must not be empty"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "customerAddress.city",
                "customer.customerAddress.city.empty",
                "City must not be empty"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "customerAddress.state",
                "customer.customerAddress.state.empty",
                "State must not be empty"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "customerAddress.country",
                "customer.customerAddress.country.empty",
                "Country must not be empty"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "customerAddress.zipCode",
                "customer.customerAddress.zipCode.empty",
                "Zip Code must not be empty"
        );

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors,
                "customerAddress.phoneNumber",
                "customer.customerAddress.phoneNumber.empty",
                "Phone number must not be empty"
        );

        if(customer.getCustomerAddress().getPhoneNumber() != null && customer.getCustomerAddress().getPhoneNumber().length() != 10){
            errors.rejectValue(
                    "customerSSN",
                    "customer.customerAddress.phoneNumber.invalidLength",
                    "total amount of numbers in your Phone # must be 10"
            );
        }
    }
}
