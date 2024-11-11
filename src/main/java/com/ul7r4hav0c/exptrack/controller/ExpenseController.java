package com.ul7r4hav0c.exptrack.controller;

import com.ul7r4hav0c.exptrack.model.Expense;
import com.ul7r4hav0c.exptrack.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Display the list of all expenses
    @GetMapping("/")
    public String showExpenses(Model model) {
        model.addAttribute("expenses", expenseRepository.findAll());
        return "home";  // Refers to home.html
    }

    // Display the form for adding a new expense
    @GetMapping("/expenses/new")
    public String showAddForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "add-expense";  // Refers to add-expense.html
    }

    // Handle form submission for adding a new expense
    @PostMapping("/expenses")
    public String addExpense(@ModelAttribute Expense expense) {
        expenseRepository.save(expense);
        return "redirect:/";  // Redirect to home page after saving
    }

    // Display the form for editing an existing expense
    @GetMapping("/expenses/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense id"));
        model.addAttribute("expense", expense);
        return "edit-expense";  // Refers to edit-expense.html
    }

    // Handle form submission for editing an existing expense
    @PostMapping("/expenses/{id}")
    public String updateExpense(@PathVariable Long id, @ModelAttribute Expense expenseDetails) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense id"));
        expense.setDescription(expenseDetails.getDescription());
        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        expense.setDate(expenseDetails.getDate());
        expenseRepository.save(expense);
        return "redirect:/";  // Redirect to home page after saving
    }

    // Display the confirmation page to delete an expense
    @GetMapping("/expenses/delete/{id}")
    public String confirmDelete(@PathVariable Long id, Model model) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense id"));
        model.addAttribute("expense", expense);
        return "delete-expense";  // Refers to delete-expense.html
    }

    // Handle deleting an expense
    @PostMapping("/expenses/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense id"));
        expenseRepository.delete(expense);
        return "redirect:/";  // Redirect to home page after deletion
    }
}
