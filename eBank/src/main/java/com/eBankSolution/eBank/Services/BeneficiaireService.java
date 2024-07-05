package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Repository.BeneficiaireRepository;
import com.eBankSolution.eBank.models.Beneficiaire;
import com.eBankSolution.eBank.models.CompteBancaire;
import com.eBankSolution.eBank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaireService {
    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    public List<Beneficiaire> getAllBeneficiaire() {
        return beneficiaireRepository.findAll();
    }

    public Beneficiaire saveBeneficiaire(Beneficiaire beneficiaire){
        return beneficiaireRepository.save(beneficiaire);
    }
    public void deleteBeneficiaire(Integer id) {
        beneficiaireRepository.deleteById(id);
    }
    public Beneficiaire getBeneficiaireById(Integer id) {
        return beneficiaireRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
