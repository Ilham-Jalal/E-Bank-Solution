package com.eBankSolution.eBank;

import com.eBankSolution.eBank.Enum.TypeC;
import com.eBankSolution.eBank.Repository.BeneficiaireRepository;
import com.eBankSolution.eBank.Services.BeneficiaireService;
import com.eBankSolution.eBank.models.Beneficiaire;
import com.eBankSolution.eBank.models.CompteBancaire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeneficiaireServiceTest {

    @Mock
    private BeneficiaireRepository beneficiaireRepository;

    @InjectMocks
    private BeneficiaireService beneficiaireService;

    private Beneficiaire beneficiaire;
    private CompteBancaire compteBancaire;

    @BeforeEach
    void setUp() {
        compteBancaire = CompteBancaire.builder()
                .compteId(1)
                .type(TypeC.COURANT)
                .solde(1000.0)
                .build();

        beneficiaire = Beneficiaire.builder()
                .beneficiaireId(1)
                .beneficiaireName("Beneficiaire Test")
                .numeroCompte(123456789)
                .banque("Banque Test")
                .compteB(compteBancaire)
                .transaction(new ArrayList<>())
                .build();
    }

    @Test
    void testGetAllBeneficiaire() {
        List<Beneficiaire> beneficiaires = List.of(beneficiaire);
        when(beneficiaireRepository.findAll()).thenReturn(beneficiaires);

        List<Beneficiaire> result = beneficiaireService.getAllBeneficiaire();

        assertEquals(1, result.size());
        assertEquals("Beneficiaire Test", result.get(0).getBeneficiaireName());
    }

    @Test
    void testSaveBeneficiaire() {
        when(beneficiaireRepository.save(any(Beneficiaire.class))).thenReturn(beneficiaire);

        Beneficiaire result = beneficiaireService.saveBeneficiaire(beneficiaire);

        assertNotNull(result);
        assertEquals("Beneficiaire Test", result.getBeneficiaireName());
    }

    @Test
    void testDeleteBeneficiaire() {
        // No return value for void method, just verify behavior
        beneficiaireService.deleteBeneficiaire(1);

        verify(beneficiaireRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetBeneficiaireById() {
        when(beneficiaireRepository.findById(1)).thenReturn(Optional.of(beneficiaire));

        Beneficiaire result = beneficiaireService.getBeneficiaireById(1);

        assertNotNull(result);
        assertEquals("Beneficiaire Test", result.getBeneficiaireName());
    }
}
