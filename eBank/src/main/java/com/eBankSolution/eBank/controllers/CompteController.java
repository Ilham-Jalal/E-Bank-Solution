package com.eBankSolution.eBank.controllers;

import com.eBankSolution.eBank.Services.CompteBancaireService;
import com.eBankSolution.eBank.models.CompteBancaire;
import com.eBankSolution.eBank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
