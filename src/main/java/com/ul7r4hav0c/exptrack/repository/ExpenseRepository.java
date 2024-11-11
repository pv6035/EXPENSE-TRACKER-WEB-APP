package com.ul7r4hav0c.exptrack.repository;

import com.ul7r4hav0c.exptrack.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Basic CRUD operations are already provided by JpaRepository
}
