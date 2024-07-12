package com.eBankSolution.eBank.Repository;

import com.eBankSolution.eBank.models.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,Integer> {
    @Query(value = "SELECT * from compte_bancaire where user_id=?", nativeQuery = true)
    List<CompteBancaire> findByUserUserId(Integer userId);


    @Query(value = "SELECT * from compte_bancaire where numero_compte=?",nativeQuery = true)
    CompteBancaire findCompteBancaireBynumeroCompte(String numeroCompte);
    @Query(value = "delete * from compte_bancaire where user_id=? ", nativeQuery = true)
    void deleteByUserUserId(Long compteId);
}
