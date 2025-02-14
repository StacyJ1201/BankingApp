package com.synergisticit.repository;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("Select Max(a.accountId) FROM Account a")
    Long findMaxAccountId();

    List<Account> findAccountsByAccountHolder(String username);
}
