package com.eBankSolution.eBank.Repository;

import com.eBankSolution.eBank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction ,Integer> {
}
