package com.eBankSolution.eBank.Repository;

import com.eBankSolution.eBank.models.CarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Integer> {
}
