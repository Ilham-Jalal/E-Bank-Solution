package com.eBankSolution.eBank.Repository;

import com.eBankSolution.eBank.models.Beneficiaire;
import com.eBankSolution.eBank.models.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire,Integer> {
    @Query(value = "SELECT * from beneficiaire where numero_compte=?", nativeQuery = true)
    List<Beneficiaire> findBeneficiaireBynumeroCompte(String numeroCompte);

}
