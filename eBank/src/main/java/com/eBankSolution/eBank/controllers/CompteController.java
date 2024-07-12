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

    @PostMapping("/save")
    public CompteBancaire saveAccount(@RequestBody CompteBancaire compteBancaire){
        return compteBancaireService.saveAccount(compteBancaire);
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
    public CompteBancaire updateCompte( @RequestBody CompteBancaire compteBancaire) {
        return compteBancaireService.saveAccount(compteBancaire);
    }

}
