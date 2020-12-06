package com.skilldistillery.giftr.controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giftr.entities.Budget;
import com.skilldistillery.giftr.services.BudgetService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class BudgetController {

	@Autowired
	private BudgetService budgetSvc;

	private String username = "11";

	@GetMapping("budgets")
	public Set<Budget> index() {
		return budgetSvc.index(username);
	}

	@GetMapping("budgets/{bid}")
	public Budget show(HttpServletResponse res, @PathVariable int bid) {

		try {
			Budget budget = budgetSvc.show(username, bid);
			res.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}

		return budgetSvc.show(username, bid);
	}

	@PostMapping("budgets")
	public Budget create(@RequestBody Budget budget, HttpServletRequest req, HttpServletResponse res) {

		try {
			budget = budgetSvc.create(username, budget);
			res.setStatus(201);
			res.setHeader("Location", "api/budgets/" + budget.getId());
		} catch (Exception e) {
			res.setStatus(400);
		}
		return budget;
	}

	@PutMapping("budgets/{bid}")
	public Budget update(HttpServletRequest req, HttpServletResponse res, @PathVariable int bid,
			@RequestBody Budget budget) {
		System.err.println(budget);
		try {
			budget = budgetSvc.update(username, bid, budget);
			res.setStatus(201);
			res.setHeader("Location", "api/budgets/" + budget.getId());
		} catch (Exception e) {
			res.setStatus(400);
		}
		System.err.println(budget);
		return budget;
	}

	@DeleteMapping("budgets/{bid}")
	public void disable(HttpServletRequest req, HttpServletResponse res, @PathVariable int bid) {
		try {
			boolean disabled = budgetSvc.disable(username, bid);
			if (disabled) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
			res.setHeader("Location", "api/budgets/");
		} catch (Exception e) {
			res.setStatus(400);
		}
	}
}
