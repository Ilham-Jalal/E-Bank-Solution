package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Repository.CarteBancaireRepository;
import com.eBankSolution.eBank.Repository.CompteBancaireRepository;
import com.eBankSolution.eBank.Repository.TransactionRepository;
import com.eBankSolution.eBank.Repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private CarteBancaireService carteBancaireService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarteBancaireRepository carteBancaireRepository;

    public List<CompteBancaire> getAllAccount(Integer userId) {
        return compteBancaireRepository.findByUserUserId(userId);
    }

    public CompteBancaire saveAccount(Long userId, CompteBancaire compteBancaire) {
        User user = userRepository.findById(userId).get();
        CompteBancaire compte1 = compteBancaireRepository.save(compteBancaire);
        CarteBancaire carteBancaire = carteBancaireService.ajouterCarte(compte1);
        if (carteBancaire != null) {
            compteBancaire.getCarteBancaires().add(carteBancaire);
            carteBancaire.setDateExpiration(generateDateCreation());
        }
        compteBancaire.setNumeroCompte(generateAccountNumber());
        compteBancaire.setUser(user);
        return compteBancaireRepository.save(compteBancaire);
    }

    public CompteBancaire updateAccount(Integer id, CompteBancaire compteBancaire) {
        Optional<CompteBancaire> existingCompte = compteBancaireRepository.findById(id);
        if (existingCompte.isPresent()) {
            CompteBancaire updatedCompte = existingCompte.get();
            updatedCompte.setType(compteBancaire.getType());
            updatedCompte.setSolde(compteBancaire.getSolde());
            updatedCompte.setDateCreation(compteBancaire.getDateCreation());
            updatedCompte.setAccountClossed(compteBancaire.getAccountClossed());
            updatedCompte.setRaisonClosing(compteBancaire.getRaisonClosing());
            updatedCompte.setNumeroCompte(compteBancaire.getNumeroCompte());
            // تحديث باقي الخصائص ديال الحساب حسب الحاجة
            return compteBancaireRepository.save(updatedCompte);
        } else {
            throw new RuntimeException("الحساب البنكي غير موجود");
        }
    }


    public String generateAccountNumber() {
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

    public void closeAccount(Integer id, String reason) {
        Optional<CompteBancaire> optionalCompteBancaire = getCompteById(id);
        if (optionalCompteBancaire.isPresent()) {
            CompteBancaire compteBancaire = optionalCompteBancaire.get();
            compteBancaire.setAccountClossed(true);
            compteBancaire.setRaisonClosing(reason);
            compteBancaireRepository.save(compteBancaire);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }
    public Optional<CompteBancaire> getCompteById(Integer id) {
        return compteBancaireRepository.findById(id);
    }

}