package com.skilldistillery.giftr.services;

import java.util.Set;

import com.skilldistillery.giftr.entities.Budget;

public interface BudgetService {

	public Set<Budget> index(String username);

	public Budget show(String username, int bid);

	public Budget create(String username, Budget budget);

	public Budget update(String username, int bid, Budget event);

	public boolean disable(String username, int bid);

}
