package com.synergisticit.repository;

import com.synergisticit.domain.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {

    List<BankTransaction> findBankTransactionsByUsername(String username);
}
