package com.eBankSolution.eBank.controllers;

import com.eBankSolution.eBank.Services.BeneficiaireService;
import com.eBankSolution.eBank.models.Beneficiaire;
import com.eBankSolution.eBank.models.CompteBancaire;
import com.eBankSolution.eBank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiaire/api/")
public class BeneficiaireController {
    @Autowired
    private BeneficiaireService beneficiaireService;

    @GetMapping("/all")
    public ResponseEntity<List<Beneficiaire>> getAllBeneficiaire() {
        List<Beneficiaire> Beneficiaires = beneficiaireService.getAllBeneficiaire();
        return ResponseEntity.ok(Beneficiaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiaire> getBeneficiaireById(@PathVariable Integer beneficiaireId) {
        Beneficiaire beneficiaire=beneficiaireService.getBeneficiaireById(beneficiaireId);

        return ResponseEntity.ok(beneficiaire);
    }
    @PostMapping("/save")
    public Beneficiaire saveBeneficiaire(@RequestBody Beneficiaire beneficiaire){
        return beneficiaireService.saveBeneficiaire(beneficiaire);
    }

    @DeleteMapping("/{beneficiaireId}")
    public void deleteBeneficiere(@PathVariable Integer beneficiaireId) {
        beneficiaireService.deleteBeneficiaire(beneficiaireId);
    }

    @PutMapping("up/{id}")
    public ResponseEntity<Beneficiaire> updateUser(@PathVariable Integer id, @RequestBody Beneficiaire beneficiaire1) {
        Beneficiaire beneficiaire = beneficiaireService.getBeneficiaireById(id);
            beneficiaire.setBeneficiaireName(beneficiaire1.getBeneficiaireName());
            beneficiaire.setBanque(beneficiaire1.getBanque());
            beneficiaire.setNumeroCompte(beneficiaire1.getNumeroCompte());
            beneficiaire.setCompteB(beneficiaire1.getCompteB());
            Beneficiaire updatedBeneficiaire = beneficiaireService.saveBeneficiaire(beneficiaire);
            return ResponseEntity.ok(updatedBeneficiaire);
    }
}
