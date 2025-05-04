package com.bankapp.controller;

import com.bankapp.model.BankAccount;
import com.bankapp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*") //
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    @PostMapping
    public BankAccount createAccount(@RequestBody BankAccount account) {
        return service.createAccount(account);
    }

    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/{id}")
    public BankAccount getAccountById(@PathVariable Long id) {
        return service.getAccountById(id).orElseThrow();
    }

    @PutMapping("/deposit/{id}")
    public BankAccount deposit(@PathVariable Long id, @RequestParam double amount) {
        return service.deposit(id, amount);
    }

    @PutMapping("/withdraw/{id}")
    public BankAccount withdraw(@PathVariable Long id, @RequestParam double amount) {
        return service.withdraw(id, amount);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
    }
}
