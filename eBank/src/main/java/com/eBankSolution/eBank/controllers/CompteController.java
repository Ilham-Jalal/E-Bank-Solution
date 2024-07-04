package com.eBankSolution.eBank.controllers;

import com.eBankSolution.eBank.Services.CompteBancaireService;
import com.eBankSolution.eBank.models.CompteBancaire;
import com.eBankSolution.eBank.models.Transaction;
import com.eBankSolution.eBank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compte/api")
public class CompteController {
    @Autowired
    private CompteBancaireService compteBancaireService;

    @GetMapping("/all")
    public ResponseEntity<List<CompteBancaire>> getAllAccount(){
        List<CompteBancaire> comptes =compteBancaireService.getAllAccount();
        return ResponseEntity.ok(comptes);
    }
    @PostMapping("/save")
    public CompteBancaire saveAccount(@RequestBody CompteBancaire compteBancaire){
        return compteBancaireService.saveAccount(compteBancaire);
    }
    @PutMapping("/{id}/{RaisonClosing}")
    public void closeAccount(@PathVariable Integer id, @PathVariable String RaisonClosing) {
        compteBancaireService.closeAccount(id, RaisonClosing);
    }
    @PutMapping("/update/{id}")
    public CompteBancaire updateCompte(@RequestBody CompteBancaire compteBancaire) {
        return compteBancaireService.saveAccount(compteBancaire);
    }

}
