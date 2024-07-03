package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Repository.CompteBancaireRepository;
import com.eBankSolution.eBank.models.CompteBancaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteBancaireService {
    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    public List<CompteBancaire> getAllAccount() {
        return compteBancaireRepository.findAll();

    }
}