package com.eBankSolution.eBank.Repository;

import com.eBankSolution.eBank.models.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,Integer> {
}
