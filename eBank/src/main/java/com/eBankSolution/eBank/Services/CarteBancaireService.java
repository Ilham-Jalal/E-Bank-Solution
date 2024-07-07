package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Enum.Status;
import com.eBankSolution.eBank.Repository.CarteBancaireRepository;
import com.eBankSolution.eBank.models.CarteBancaire;
import com.eBankSolution.eBank.models.CompteBancaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Random;

@Service
public class CarteBancaireService {
    @Autowired
    private CarteBancaireRepository carteBancaireRepository;

    public List<CarteBancaire> getAllCarte() {
        return carteBancaireRepository.findAll();
    }

    public CarteBancaire saveCarte(CarteBancaire carteBancaire){
        return carteBancaireRepository.save(carteBancaire);
    }

    public CarteBancaire ajouterCarte(CompteBancaire compteBancaire){
        CarteBancaire carteBancaire = new CarteBancaire();
        carteBancaire.setNumero(generateCardNumber());
        carteBancaire.setDateExpiration(generateExpirationDate());
        carteBancaire.setCompteB(compteBancaire);
        carteBancaire.setStatus(Status.activated);
        return carteBancaireRepository.save(carteBancaire);
    }

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + (5L * 365 * 24 * 60 * 60 * 1000));
    }

    public CarteBancaire activerCarte(Integer carteId) {
        CarteBancaire carteBancaire = carteBancaireRepository.findById(carteId).orElseThrow(() -> new RuntimeException("Carte non trouvée"));
        carteBancaire.setStatus(Status.activated);
        return carteBancaireRepository.save(carteBancaire);
    }

    public CarteBancaire desactiverCarte(Integer carteId) {
        CarteBancaire carteBancaire = carteBancaireRepository.findById(carteId).orElseThrow(() -> new RuntimeException("Carte non trouvée"));
        carteBancaire.setStatus(Status.desactivated);
        return carteBancaireRepository.save(carteBancaire);
    }

    public CarteBancaire bloquerCarte(Integer carteId, String raison) {
        CarteBancaire carteBancaire = carteBancaireRepository.findById(carteId).orElseThrow(() -> new RuntimeException("Carte non trouvée"));
        carteBancaire.setStatus(Status.blocked);
        carteBancaire.setRaisonBlocage(raison);
        return carteBancaireRepository.save(carteBancaire);
    }
}
