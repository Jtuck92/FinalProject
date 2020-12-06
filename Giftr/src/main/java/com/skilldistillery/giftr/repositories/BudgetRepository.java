package com.skilldistillery.giftr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
}
