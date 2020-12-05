package com.skilldistillery.giftr.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BudgetTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Budget budget;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("giftrPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		budget = em.find(Budget.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		budget = null;
	}

	@Test
	@DisplayName("Test Budget Entity")
	void test() {
		assertNotNull(budget);
		assertEquals(5, budget.getLowPrice());

	}
	@Test
	@DisplayName("Test Budget to Event Entity")
	void test1() {
		assertNotNull(budget);
		assertNotNull(budget.getEvents());
		assertTrue(budget.getEvents().size()>0);
		assertEquals("Giftr Event Name", budget.getEvents().get(0).getName());
		
	}
}
