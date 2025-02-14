package com.synergisticit.repository;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("Select Max(c.customerId) FROM Customer c")
    Long findMaxCustomerId();

    boolean existsByUser(User user);

    Customer findCustomerByUser(User user);
}
