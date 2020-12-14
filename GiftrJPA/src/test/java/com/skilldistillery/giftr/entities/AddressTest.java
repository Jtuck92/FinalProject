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

public class AddressTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Address address;

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
		address = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null;
	}

	@Test
	@DisplayName("Test Address Entity")
	void test() {
		assertNotNull(address);
		assertEquals("11111", address.getZip());

	}
	@Test
	@DisplayName("Test Address to User")
	void test1() {
		assertNotNull(address);
		assertNotNull(address.getUsers());
		assertTrue(address.getUsers().size()>0);
		assertEquals("11", address.getUsers().get(0).getUsername());
		
	}
	@Test
	@DisplayName("Test Address to Payment")
	void test2() {
		assertNotNull(address);
		assertNotNull(address.getPayments());
		assertTrue(address.getPayments().size()>0);
		assertEquals("1111111111111111", address.getPayments().get(0).getCardNumber());
		assertEquals(11.11, address.getPayments().get(0).getAmount());
		
	}
}
