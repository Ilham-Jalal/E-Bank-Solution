package com.eBankSolution.eBank.Repository;

import com.eBankSolution.eBank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction ,Integer> {
}
