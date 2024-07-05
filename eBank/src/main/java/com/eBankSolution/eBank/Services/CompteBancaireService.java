package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Repository.CarteBancaireRepository;
import com.eBankSolution.eBank.Repository.CompteBancaireRepository;
import com.eBankSolution.eBank.Repository.TransactionRepository;
import com.eBankSolution.eBank.models.CarteBancaire;
import com.eBankSolution.eBank.models.CompteBancaire;
import com.eBankSolution.eBank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class CompteBancaireService {
    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CarteBancaireService carteBancaireService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarteBancaireRepository carteBancaireRepository;

    public List<CompteBancaire> getAllAccount() {
        return compteBancaireRepository.findByUserUserId();
    }
    public CompteBancaire saveAccount(CompteBancaire compteBancaire){
        CompteBancaire compte1 = compteBancaireRepository.save(compteBancaire);
        CarteBancaire carteBancaire = carteBancaireService.ajouterCarte(compte1);
        compteBancaire.getCarteBancaires().add(carteBancaire);
        compteBancaire.setNumeroCompte(generateAccountNumber());
        carteBancaire.setDateExpiration(generateDateCreation());
        return compteBancaireRepository.save(compteBancaire);
    }


    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder AccountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            AccountNumber.append(random.nextInt(10));
        }
        return AccountNumber.toString();
    }
    private java.sql.Date generateDateCreation() {
        return new Date(System.currentTimeMillis() + (5L * 365 * 24 * 60 * 60 * 1000));
    }
//    public void closeAccount(Integer id, String RaisonClosing) {
//        CompteBancaire compteBancaire = compteBancaireRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
//
//        if (compteBancaire.getSolde() != 0) {
//            throw new IllegalStateException("Account balance must be zero before closing.");
//        }
//
//        compteBancaire.setAccountClossed(true);
//        compteBancaire.setRaisonClosing(RaisonClosing);
//
//        compteBancaireRepository.save(compteBancaire);
//    }


    public void closeAccount(Integer id, String reason) {
        Optional<CompteBancaire> optionalCompteBancaire = getCompteById(id);
        if (optionalCompteBancaire.isPresent()) {
            CompteBancaire compteBancaire = optionalCompteBancaire.get();
            compteBancaire.setAccountClossed(true);
            compteBancaire.setRaisonClosing(reason);
            saveAccount(compteBancaire);  // Assuming saveAccount method persists the entity.
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }
    public Optional<CompteBancaire> getCompteById(Integer id) {
        return compteBancaireRepository.findById(id);
    }

}