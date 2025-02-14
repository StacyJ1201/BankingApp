package com.synergisticit.service;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import com.synergisticit.repository.CustomerRepository;
import com.synergisticit.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Customer> findAllCustomers() { return customerRepository.findAll();}

    @Override
    public Customer saveCustomer(Customer customer) {

        if (customer.getUser() == null) {
            throw new IllegalArgumentException("Customer must have an associated user");
        }

        Optional<User> user = userRepository.findById(customer.getUser().getUserId());

        if(customerRepository.existsByUser(user.orElse(null))){
            throw new IllegalStateException("User already has an associated customer");
        }

        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Customer updateCustomerById(Long customerId, Customer customer) {
        Optional<Customer> optCustomer = findCustomerById(customerId);
        if(optCustomer.isPresent()){
            Customer updatedCustomer = new Customer();

            updatedCustomer.setCustomerGender(customer.getCustomerGender());
            updatedCustomer.setCustomerAccounts(customer.getCustomerAccounts());
            updatedCustomer.setCustomerDOB(customer.getCustomerDOB());
            updatedCustomer.setCustomerAddress(customer.getCustomerAddress());
            updatedCustomer.setCustomerName(customer.getCustomerName());
            updatedCustomer.setCustomerSSN(customer.getCustomerSSN());
            updatedCustomer.setUser(customer.getUser());
            updatedCustomer.setCustomerId(customerId);

            return customerRepository.save(updatedCustomer);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer findCustomerByUser(User user) {
        return customerRepository.findCustomerByUser(user);
    }
}
