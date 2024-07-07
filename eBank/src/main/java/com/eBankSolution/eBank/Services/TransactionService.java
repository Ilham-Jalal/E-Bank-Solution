package com.eBankSolution.eBank.Services;

import com.eBankSolution.eBank.Repository.CompteBancaireRepository;
import com.eBankSolution.eBank.Repository.TransactionRepository;
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
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
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

    public void internalTransfer(Integer fromAccountId, Integer toAccountId, Double amount, String description) {
        CompteBancaire fromAccount = compteBancaireRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + fromAccountId));

        CompteBancaire toAccount = compteBancaireRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + toAccountId));

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

    public void externalTransfer(Integer fromAccountId, String toAccountNumber, String bankName, Double amount, String description) {
        CompteBancaire fromAccount = compteBancaireRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + fromAccountId));

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
                .build();

        compteBancaireRepository.save(fromAccount);
        transactionRepository.save(transaction);
    }
}
