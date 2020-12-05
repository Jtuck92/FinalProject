package com.skilldistillery.giftr.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Payment payment;

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
		payment = em.find(Payment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		payment = null;
	}

	@Test
	void test() {
		assertNotNull(payment);
		assertEquals("1111", payment.getCardNumber());
		assertEquals(11.11, payment.getAmount());
	}
	@Test
	@DisplayName("Test Payment to Address")
	void test1() {
		assertNotNull(payment);
		assertNotNull(payment.getAddress());
		assertEquals("11111", payment.getAddress().getZip());
		assertEquals("Street Name 1", payment.getAddress().getStreet());
		
	}
	@Test
	@DisplayName("Test Payment to User")
	void test2() {
		assertNotNull(payment);
		assertNotNull(payment.getUser());
		assertEquals("11", payment.getUser().getUsername());
		
	}

}
