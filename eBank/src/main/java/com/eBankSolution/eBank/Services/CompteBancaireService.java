package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Repository.CompteBancaireRepository;
import com.eBankSolution.eBank.Repository.TransactionRepository;
import com.eBankSolution.eBank.models.CompteBancaire;
import com.eBankSolution.eBank.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteBancaireService {
    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<CompteBancaire> getAllAccount() {
        return compteBancaireRepository.findAll();
    }
    public CompteBancaire saveAccount(CompteBancaire compteBancaire){
        return compteBancaireRepository.save(compteBancaire);
    }

    public void closeAccount(Integer id, String RaisonClosing) {
        CompteBancaire compteBancaire = compteBancaireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (compteBancaire.getSolde() != 0) {
            throw new IllegalStateException("Account balance must be zero before closing.");
        }

        compteBancaire.setAccountClossed(true);
        compteBancaire.setRaisonClosing(RaisonClosing);

        compteBancaireRepository.save(compteBancaire);
    }
    public Optional<CompteBancaire> getCompteById(Integer id) {
        return compteBancaireRepository.findById(id);
    }

}