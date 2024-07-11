package com.eBankSolution.eBank;

import com.eBankSolution.eBank.Enum.Status;
import com.eBankSolution.eBank.Enum.TypeC;
import com.eBankSolution.eBank.Repository.CarteBancaireRepository;
import com.eBankSolution.eBank.Services.CarteBancaireService;
import com.eBankSolution.eBank.models.CarteBancaire;
import com.eBankSolution.eBank.models.CompteBancaire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarteBancaireServiceTest {

    @Mock
    private CarteBancaireRepository carteBancaireRepository;

    @InjectMocks
    private CarteBancaireService carteBancaireService;

    private CompteBancaire compteBancaire;
    private CarteBancaire carteBancaire;

    @BeforeEach
    void setUp() {
        compteBancaire = CompteBancaire.builder()
                .compteId(1)
                .type(TypeC.COURANT.COURANT)
                .solde(1000.0)
                .dateCreation(new Date(System.currentTimeMillis()))
                .accountClossed(false)
                .numeroCompte("1234567890")
                .user(null)
                .beneficiaire(new ArrayList<>())
                .carteBancaires(new ArrayList<>())
                .Transaction(new ArrayList<>())
                .build();
        carteBancaire = CarteBancaire.builder()
                .carteId(1)
                .numero("1234567812345678")
                .dateExpiration(new Date(System.currentTimeMillis() + (5L * 365 * 24 * 60 * 60 * 1000)))
                .status(Status.activated)
                .compteB(compteBancaire)
                .build();
    }

    @Test
    void testAjouterCarte() {
        when(carteBancaireRepository.save(any(CarteBancaire.class))).thenReturn(carteBancaire);

        CarteBancaire result = carteBancaireService.ajouterCarte(compteBancaire);

        assertNotNull(result);
        assertEquals("1234567812345678", result.getNumero());
        assertEquals(Status.activated, result.getStatus());
        verify(carteBancaireRepository, times(1)).save(any(CarteBancaire.class));
    }

    @Test
    void testActiverCarte() {
        when(carteBancaireRepository.findById(1)).thenReturn(Optional.of(carteBancaire));
        when(carteBancaireRepository.save(any(CarteBancaire.class))).thenReturn(carteBancaire);

        CarteBancaire result = carteBancaireService.activerCarte(1);

        assertNotNull(result);
        assertEquals(Status.activated, result.getStatus());
        verify(carteBancaireRepository, times(1)).findById(1);
        verify(carteBancaireRepository, times(1)).save(any(CarteBancaire.class));
    }

    @Test
    void testDesactiverCarte() {
        when(carteBancaireRepository.findById(1)).thenReturn(Optional.of(carteBancaire));
        when(carteBancaireRepository.save(any(CarteBancaire.class))).thenReturn(carteBancaire);

        CarteBancaire result = carteBancaireService.desactiverCarte(1);

        assertNotNull(result);
        assertEquals(Status.desactivated, result.getStatus());
        verify(carteBancaireRepository, times(1)).findById(1);
        verify(carteBancaireRepository, times(1)).save(any(CarteBancaire.class));
    }

    @Test
    void testBloquerCarte() {
        when(carteBancaireRepository.findById(1)).thenReturn(Optional.of(carteBancaire));
        when(carteBancaireRepository.save(any(CarteBancaire.class))).thenReturn(carteBancaire);

        CarteBancaire result = carteBancaireService.bloquerCarte(1, "Suspicious activity");

        assertNotNull(result);
        assertEquals(Status.blocked, result.getStatus());
        assertEquals("Suspicious activity", result.getRaisonBlocage());
        verify(carteBancaireRepository, times(1)).findById(1);
        verify(carteBancaireRepository, times(1)).save(any(CarteBancaire.class));
    }
}
