package com.synergisticit.controller;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import com.synergisticit.repository.CustomerRepository;
import com.synergisticit.repository.UserRepository;
import com.synergisticit.service.CustomerService;
import com.synergisticit.validator.CustomerValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerValidator customerValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(customerValidator);
    }

    @GetMapping("/customerForm")
    public String showCustomerForm(Model model){
        Customer customer = new Customer();
        if(customerRepository.findMaxCustomerId() != null){
            customer.setCustomerId(customerRepository.findMaxCustomerId() + 1);
        } else {
            customer.setCustomerId(1L);
        }
        model.addAttribute("customer", customer);
        model.addAttribute("customers", customerService.findAllCustomers());
        return "customerForm";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult br, Model model, Authentication authentication){
        if(br.hasErrors()){
            model.addAttribute("customers", customerService.findAllCustomers());
            return "customerForm";
        }
        String username = authentication.getName();
        System.out.println("Current logged in user: " + username);
        Optional<User> loggedInUser = userRepository.findUserByUsername(username);

        if (customerRepository.existsByUser(loggedInUser.get())) {
            model.addAttribute("error", "You already have a customer profile");
            model.addAttribute("customers", customerService.findAllCustomers());
            return "customerForm";
        }

        customer.setUser(loggedInUser.get());
        customerService.saveCustomer(customer);
        return "redirect:/customerForm";
    }

    @GetMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable Long id, Model model){
        Optional<Customer> foundCustomer = customerService.findCustomerById(id);
        if(foundCustomer.isPresent()){
            model.addAttribute("customer", foundCustomer.get());
            model.addAttribute("customers", customerService.findAllCustomers());
            return "customerForm";
        } else {
            return "customerForm";
        }
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        return "redirect:/customerForm";
    }
}
