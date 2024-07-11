package com.eBankSolution.eBank.controllers;

import com.eBankSolution.eBank.Services.TransactionService;
import com.eBankSolution.eBank.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction/api/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/all/{compteId}")
    public List<Transaction> getTransactions(@PathVariable Long compteId){
        return transactionService.getAccountTransactions(compteId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/save")
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
    }

    @PostMapping("/internal-transfer")
    public ResponseEntity<String> internalTransfer(@RequestParam Integer fromAccountId, @RequestParam String numeroCompte, @RequestParam Double amount, @RequestParam String description) {
        transactionService.internalTransfer(fromAccountId, numeroCompte, amount, description);
        return ResponseEntity.ok("Internal transfer successful");
    }

    @PostMapping("/external-transfer")
    public ResponseEntity<String> externalTransfer(@RequestParam Integer fromAccountId, @RequestParam String toAccountNumber, @RequestParam String bankName, @RequestParam Double amount, @RequestParam String description) {
        transactionService.externalTransfer(fromAccountId, toAccountNumber, bankName, amount, description);
        return ResponseEntity.ok("External transfer successful");
    }
}
