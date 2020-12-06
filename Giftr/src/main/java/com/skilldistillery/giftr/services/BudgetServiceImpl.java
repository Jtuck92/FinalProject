package com.skilldistillery.giftr.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.Budget;
import com.skilldistillery.giftr.entities.Event;
import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.BudgetRepository;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class BudgetServiceImpl implements BudgetService {

	@Autowired
	private BudgetRepository budgetRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Set<Budget> index(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Set<Budget> budgets = new HashSet<Budget>();
			List<Budget> all = budgetRepo.findAll();
			for (Budget budget : all) {
				budgets.add(budget);
			}
			return budgets;
		}
		return null;
	}

	@Override
	public Budget show(String username, int bid) {
		User user = userRepo.findByUsername(username);
		if (user != null) {

			Optional<Budget> budgetOpt = budgetRepo.findById(bid);
			Budget budget = null;
			if (budgetOpt.isPresent()) {
				budget = budgetOpt.get();
				if (budget.getEnabled() == true) {
					return budget;
				}
			}
		}
		return null;
	}

	@Override
	public Budget create(String username, Budget budget) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			budgetRepo.saveAndFlush(budget);
			userRepo.save(user);
			return budget;
		}
		return null;
	}

	@Override
	public Budget update(String username, int bid, Budget budget) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Optional<Budget> ePOpt = budgetRepo.findById(bid);
			Budget managedBudget = null;
			if (ePOpt.isPresent()) {
				managedBudget = ePOpt.get();
				if (managedBudget.getEnabled() == true) {
					if (managedBudget.getLowPrice() != null) {
						managedBudget.setLowPrice(budget.getLowPrice());
					}
					if (managedBudget.getHighPrice() != null) {
						managedBudget.setHighPrice(budget.getHighPrice());
					}
					if (managedBudget.getEvents() != null) {
						managedBudget.setEvents(budget.getEvents());
					}
					budgetRepo.saveAndFlush(managedBudget);
					userRepo.saveAndFlush(user);
					return managedBudget;
				}
			}
		}
		return null;
	}

	@Override
	public boolean disable(String username, int id) {
		boolean disabled = false;
		Optional<Budget> budgetOpt = budgetRepo.findById(id);
		User user = userRepo.findByUsername(username);
		Budget disabledBudget = null;

		if (user != null) {
			if (budgetOpt.isPresent()) {
				disabledBudget = budgetOpt.get();
				disabledBudget.setEnabled(false);
				budgetRepo.saveAndFlush(disabledBudget);
				userRepo.save(user);
				disabled = true;
				return disabled;
			}
		}
		return disabled;
	}

}
