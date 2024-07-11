package com.eBankSolution.eBank;

import com.eBankSolution.eBank.Repository.BeneficiaireRepository;
import com.eBankSolution.eBank.Repository.CompteBancaireRepository;
import com.eBankSolution.eBank.Repository.TransactionRepository;
import com.eBankSolution.eBank.Services.TransactionService;
import com.eBankSolution.eBank.models.Beneficiaire;
import com.eBankSolution.eBank.models.CompteBancaire;
import com.eBankSolution.eBank.models.Transaction;
import com.eBankSolution.eBank.Enum.TypeT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private CompteBancaireRepository compteBancaireRepository;

    @Mock
    private BeneficiaireRepository beneficiaireRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private CompteBancaire fromAccount;
    private CompteBancaire toAccount;
    private Beneficiaire beneficiaire;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        fromAccount = CompteBancaire.builder()
                .compteId(1)  // Change to Integer
                .solde(1000.0)
                .build();

        toAccount = CompteBancaire.builder()
                .compteId(2)  // Change to Integer
                .solde(500.0)
                .build();

        beneficiaire = Beneficiaire.builder()
                .beneficiaireId(1)
                .beneficiaireName("Beneficiaire Test")
                .numeroCompte(Integer.valueOf("123456789"))
                .banque("Banque Test")
                .compteB(toAccount)
                .build();

        transaction = Transaction.builder()
                .transactionId(1)
                .date(new Date())
                .montant(100.0)
                .description("Test Transaction")
                .type(TypeT.internal)
                .compteB(fromAccount)
                .build();
    }

    @Test
    void testGetAllTransactions() {
        List<Transaction> transactions = List.of(transaction);
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.getAllTransactions();

        assertEquals(1, result.size());
        assertEquals("Test Transaction", result.get(0).getDescription());
    }

    @Test
    void testGetAccountTransactions() {
        when(transactionRepository.findAllByCompteBCompteId(1L)).thenReturn(List.of(transaction));  // Change to Integer

        List<Transaction> result = transactionService.getAccountTransactions(1L);  // Change to Integer

        assertEquals(1, result.size());
        assertEquals("Test Transaction", result.get(0).getDescription());
    }

    @Test
    void testSaveTransaction() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.saveTransaction(transaction);

        assertNotNull(result);
        assertEquals("Test Transaction", result.getDescription());
    }

    @Test
    void testGetTransactionById() {
        when(transactionRepository.findById(1)).thenReturn(java.util.Optional.of(transaction));

        Transaction result = transactionService.getTransactionById(1);

        assertNotNull(result);
        assertEquals("Test Transaction", result.getDescription());
    }

    @Test
    void testDeleteTransaction() {
        // No return value for void method, just verify behavior
        transactionService.deleteTransaction(1);

        verify(transactionRepository, times(1)).deleteById(1);
    }

    @Test
    void testInternalTransfer() {
        when(compteBancaireRepository.findById(1)).thenReturn(java.util.Optional.of(fromAccount));  // Change to Integer
        when(compteBancaireRepository.findCompteBancaireBynumeroCompte("123456789")).thenReturn(toAccount);

        transactionService.internalTransfer(1, "123456789", 100.0, "Internal Transfer Test");

        assertEquals(900.0, fromAccount.getSolde());
        assertEquals(600.0, toAccount.getSolde());
    }

    @Test
    void testExternalTransfer() {
        when(compteBancaireRepository.findById(1)).thenReturn(java.util.Optional.of(fromAccount));  // Change to Integer
        when(beneficiaireRepository.findBeneficiaireBynumeroCompte("123456789")).thenReturn(List.of(beneficiaire));

        transactionService.externalTransfer(1, "123456789", "Banque Test", 100.0, "External Transfer Test");

        assertEquals(900.0, fromAccount.getSolde());
        // Assuming external transfer doesn't change beneficiary account balance directly
    }
}
