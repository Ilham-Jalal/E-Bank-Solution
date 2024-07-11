package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Repository.BeneficiaireRepository;
import com.eBankSolution.eBank.Repository.CompteBancaireRepository;
import com.eBankSolution.eBank.Repository.TransactionRepository;
import com.eBankSolution.eBank.models.Beneficiaire;
import com.eBankSolution.eBank.models.CompteBancaire;
import com.eBankSolution.eBank.models.Transaction;
import com.eBankSolution.eBank.Enum.TypeT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAccountTransactions(Long compteId){
        return transactionRepository.findAllByCompteBCompteId(compteId);
    }
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    public void internalTransfer(Integer fromAccountId, String numeroCompte, Double amount, String description) {
        CompteBancaire fromAccount = compteBancaireRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + fromAccountId));

        CompteBancaire toAccount = compteBancaireRepository.findCompteBancaireBynumeroCompte(numeroCompte);

        if (fromAccount.getSolde() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setSolde(fromAccount.getSolde() - amount);
        toAccount.setSolde(toAccount.getSolde() + amount);

        Transaction transaction = Transaction.builder()
                .date(new Date())
                .montant(amount)
                .description(description)
                .type(TypeT.internal)
                .compteB(fromAccount)
                .build();

        compteBancaireRepository.save(fromAccount);
        compteBancaireRepository.save(toAccount);
        transactionRepository.save(transaction);
    }

    public void externalTransfer(Integer fromAccountId, String numeroCompte, String bankName, Double amount, String description) {
        CompteBancaire fromAccount = compteBancaireRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + fromAccountId));

        List<Beneficiaire> beneficiaries = beneficiaireRepository.findBeneficiaireBynumeroCompte(numeroCompte);

        if (beneficiaries.size() == 0) {
            throw new RuntimeException("No beneficiary found with account number: " + numeroCompte);
        } else if (beneficiaries.size() > 1) {
            throw new RuntimeException("Multiple beneficiaries found with account number: " + numeroCompte);
        }

        Beneficiaire beneficiaire = beneficiaries.get(0);

        if (fromAccount.getSolde() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setSolde(fromAccount.getSolde() - amount);

        Transaction transaction = Transaction.builder()
                .date(new Date())
                .montant(amount)
                .description(description)
                .banque(bankName)
                .type(TypeT.external)
                .compteB(fromAccount)
                .beneficiaire(beneficiaire)
                .build();

        compteBancaireRepository.save(fromAccount);
        transactionRepository.save(transaction);
    }

}
