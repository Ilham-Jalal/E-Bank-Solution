package com.eBankSolution.eBank.Repository;

import com.eBankSolution.eBank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction ,Integer> {
    @Query("select t from Transaction t where t.compteB.compteId= :compteId")
    List<Transaction> findAllByCompteBCompteId(@Param("compteId") Long compteId);
}
