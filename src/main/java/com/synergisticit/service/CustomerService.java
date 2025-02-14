package com.synergisticit.service;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAllCustomers();
    Customer saveCustomer(Customer customer);
    Optional<Customer> findCustomerById(Long customerId);
    Customer updateCustomerById(Long customerId, Customer customer);
    void deleteCustomerById(Long customerId);
    Customer findCustomerByUser(User user);
}
