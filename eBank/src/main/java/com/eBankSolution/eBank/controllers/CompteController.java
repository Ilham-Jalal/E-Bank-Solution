package com.eBankSolution.eBank.controllers;

import com.eBankSolution.eBank.Services.CompteBancaireService;
import com.eBankSolution.eBank.models.CompteBancaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compte/api")
public class CompteController {
    @Autowired
    private CompteBancaireService compteBancaireService;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<CompteBancaire>> getAllAccount(@PathVariable Integer userId){
        List<CompteBancaire> comptes = compteBancaireService.getAllAccount(userId);
        return ResponseEntity.ok(comptes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CompteBancaire>> getCompteById(@PathVariable Integer id) {
        Optional<CompteBancaire> compteBancaire = compteBancaireService.getCompteById(id);
        return ResponseEntity.ok(compteBancaire);
    }

    @PostMapping("/save/{userId}")
    public CompteBancaire saveAccount(@PathVariable Long userId, @RequestBody CompteBancaire compteBancaire){
        return compteBancaireService.saveAccount(userId, compteBancaire);
    }


    @PutMapping("/close/{id}")
    public ResponseEntity<Void> closeAccount(@PathVariable Integer id, @RequestBody String reason) {
        try {
            compteBancaireService.closeAccount(id, reason);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CompteBancaire> updateCompte(@PathVariable Integer id, @RequestBody CompteBancaire compteBancaire) {
        try {
            CompteBancaire updatedCompte = compteBancaireService.updateAccount(id, compteBancaire);
            return ResponseEntity.ok(updatedCompte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
