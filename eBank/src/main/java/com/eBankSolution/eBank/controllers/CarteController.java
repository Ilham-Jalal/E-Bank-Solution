package com.eBankSolution.eBank.controllers;

import com.eBankSolution.eBank.Services.CarteBancaireService;
import com.eBankSolution.eBank.models.CarteBancaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carte/api/")
public class CarteController {
    @Autowired
    private CarteBancaireService carteBancaireService;


    @GetMapping("/all")
    public ResponseEntity<List<CarteBancaire>> getAllCartes(){
        List<CarteBancaire> cartes =carteBancaireService.getAllCarte();
        return ResponseEntity.ok(cartes);
    }

    @PostMapping("/save")
    public CarteBancaire saveCarte(@RequestBody CarteBancaire carteBancaire){
        return carteBancaireService.saveCarte(carteBancaire);
    }
}
