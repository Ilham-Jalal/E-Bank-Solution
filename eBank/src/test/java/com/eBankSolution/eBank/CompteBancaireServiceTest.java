//package com.eBankSolution.eBank;
//
//import com.eBankSolution.eBank.Repository.CompteBancaireRepository;
//import com.eBankSolution.eBank.Repository.TransactionRepository;
//import com.eBankSolution.eBank.Services.CarteBancaireService;
//import com.eBankSolution.eBank.Services.CompteBancaireService;
//import com.eBankSolution.eBank.Services.UserService;
//import com.eBankSolution.eBank.models.CarteBancaire;
//import com.eBankSolution.eBank.models.CompteBancaire;
//import com.eBankSolution.eBank.Enum.TypeC;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CompteBancaireServiceTest {
//
//    @Mock
//    private CompteBancaireRepository compteBancaireRepository;
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private CarteBancaireService carteBancaireService;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private CompteBancaireService compteBancaireService;
//
//    private CompteBancaire compteBancaire;
//    private CarteBancaire carteBancaire;
//
//    @BeforeEach
//    void setUp() {
//        compteBancaire = CompteBancaire.builder()
//                .compteId(1)
//                .type(TypeC.COURANT)
//                .solde(1000.0)
//                .dateCreation(new Date(System.currentTimeMillis()))
//                .accountClossed(false)
//                .numeroCompte("1234567890")
//                .user(null)
//                .beneficiaire(new ArrayList<>())
//                .carteBancaires(new ArrayList<>())
//                .Transaction(new ArrayList<>())
//                .build();
//        carteBancaire = CarteBancaire.builder()
//                .carteId(1)
//                .compteB(compteBancaire)
//                .build();
//    }
//
//    @Test
//    void testGetAllAccount() {
//        List<CompteBancaire> comptes = List.of(compteBancaire);
//        when(compteBancaireRepository.findByUserUserId()).thenReturn(comptes);
//
//        List<CompteBancaire> result = compteBancaireService.getAllAccount();
//        assertEquals(1, result.size());
//        assertEquals(TypeC.COURANT, result.get(0).getType());
//    }
//
//    @Test
//    void testSaveAccount() {
//        // Arrange
//        CompteBancaireService spyService = spy(compteBancaireService);
//        when(spyService.generateAccountNumber()).thenReturn("1234567890");
//        when(compteBancaireRepository.save(any(CompteBancaire.class))).thenReturn(compteBancaire);
//        when(carteBancaireService.ajouterCarte(any(CompteBancaire.class))).thenReturn(carteBancaire);
//
//        // Act
//        CompteBancaire result = spyService.saveAccount(compteBancaire);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("1234567890", result.getNumeroCompte());
//        assertFalse(result.getCarteBancaires().isEmpty());
//
//        // Verify that save and ajouterCarte methods were called
//        verify(compteBancaireRepository, times(2)).save(any(CompteBancaire.class));
//        verify(carteBancaireService).ajouterCarte(any(CompteBancaire.class));
//    }
//
//
//
//    @Test
//    void testCloseAccount() {
//        // Arrange
//        when(compteBancaireRepository.findById(1)).thenReturn(Optional.of(compteBancaire));
//        when(compteBancaireRepository.save(any(CompteBancaire.class))).thenReturn(compteBancaire);
//
//        // Act
//        compteBancaireService.closeAccount(1, "Account Closed Reason");
//
//        // Assert
//        assertTrue(compteBancaire.getAccountClossed());
//        assertEquals("Account Closed Reason", compteBancaire.getRaisonClosing());
//
//        // Verify that findById and save methods were called
//        verify(compteBancaireRepository).findById(1);
//        verify(compteBancaireRepository, times(2)).save(any(CompteBancaire.class));
//    }
//
//
//
//    @Test
//    void testCloseAccount_NotFound() {
//        when(compteBancaireRepository.findById(1)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            compteBancaireService.closeAccount(1, "Account Closed Reason");
//        });
//
//        assertEquals("Account not found", exception.getMessage());
//    }
//
//    @Test
//    void testGetCompteById() {
//        when(compteBancaireRepository.findById(1)).thenReturn(Optional.of(compteBancaire));
//
//        Optional<CompteBancaire> result = compteBancaireService.getCompteById(1);
//        assertTrue(result.isPresent());
//        assertEquals(1, result.get().getCompteId());
//    }
//}
