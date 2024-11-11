package com.ul7r4hav0c.exptrack.service;

import com.ul7r4hav0c.exptrack.model.Expense;
import com.ul7r4hav0c.exptrack.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll(); // Retrieve all expenses
    }

    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        return expense.orElse(null); // Return the expense if present, otherwise null
    }

    public void saveExpense(Expense expense) {
        expenseRepository.save(expense); // Save or update the expense
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id); // Delete the expense by its ID
    }
}
