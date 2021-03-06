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

public class GiftTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Gift gift;

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
		gift = em.find(Gift.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		gift = null;
	}

	@Test
	void test() {
		assertNotNull(gift);
		assertEquals(12.5, gift.getPrice());

	}
	@Test
	void test2() {
		assertNotNull(gift);
		assertEquals(1, gift.getEvent().getId());
		
	}
	@Test
	@DisplayName("Test Gift to Gifter")
	void test3() {
		assertNotNull(gift);
		assertNotNull(gift.getGifter());
		assertEquals("11", gift.getGifter().getUsername());
		
	}
	@Test
	@DisplayName("Test Gift to Receiver")
	void test4() {
		assertNotNull(gift);
		assertNotNull(gift.getReceiver());
		assertEquals("TheDude22", gift.getReceiver().getUsername());
		
	}
}
