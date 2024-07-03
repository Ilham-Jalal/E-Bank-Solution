package com.eBankSolution.eBank.Repository;

import com.eBankSolution.eBank.models.carteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteBancaireRepository extends JpaRepository<carteBancaire, Integer> {
}
