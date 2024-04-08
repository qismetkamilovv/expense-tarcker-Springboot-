package az.keytd.expensetracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.Transaction;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findbyAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    // TODO rename to addExpense
    public void decreaseBalance(Long accountId, Double amount) {
        Transaction transaction = transactionRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("account doesn't exist"));

        Double currentAmount = transaction.getAmount();
        Double newAmount = currentAmount - amount;
        transaction.setAmount(newAmount);
        transactionRepository.save(transaction);

    }

    // TODO rename to addIncome
    public void increaseBalance(Long accountId, Double amount) {
        Transaction transaction = transactionRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("account doesn't exist"));

        Double currentAmount = transaction.getAmount();
        Double newAmount = currentAmount + amount;
        transaction.setAmount(newAmount);
        transactionRepository.save(transaction);
    }
}
