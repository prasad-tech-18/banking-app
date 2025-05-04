package com.bankapp.service;

import com.bankapp.model.BankAccount;
import com.bankapp.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    public BankAccount createAccount(BankAccount account) {
        return repository.save(account);
    }

    public List<BankAccount> getAllAccounts() {
        return repository.findAll();
    }

    public Optional<BankAccount> getAccountById(Long id) {
        return repository.findById(id);
    }

    public BankAccount deposit(Long id, double amount) {
        BankAccount account = repository.findById(id).orElseThrow();
        account.setBalance(account.getBalance() + amount);
        return repository.save(account);
    }

    public BankAccount withdraw(Long id, double amount) {
        BankAccount account = repository.findById(id).orElseThrow();
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
        } else {
            throw new RuntimeException("Insufficient balance");
        }
        return repository.save(account);
    }

    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }
}
