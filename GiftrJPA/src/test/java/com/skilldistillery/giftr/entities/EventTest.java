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

public class EventTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Event event;

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
		event = em.find(Event.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		event = null;
	}

	@Test
	@DisplayName("test Event entity")
	void test() {
		assertNotNull(event);
		assertEquals("Giftr Event Name", event.getName());

	}
	
	@Test
	@DisplayName("test Event to Budget entity")
	void test1() {
		assertNotNull(event);
		assertNotNull(event.getBudget());
		assertEquals(5, event.getBudget().getLowPrice());
	}
	@Test
	@DisplayName("test Event to Gift entity")
	void test2() {
		assertNotNull(event);
		assertNotNull(event.getGifts());
		assertTrue(event.getGifts().size() > 0);
		assertEquals(12.5, event.getGifts().get(0).getPrice());
	}
	@Test
	@DisplayName("test Event to EventPost entity")
	void test3() {
		assertNotNull(event);
		assertNotNull(event.getPosts());
		assertTrue(event.getPosts().size() > 0);
		assertEquals("New Event Description", event.getPosts().get(0).getDescription());
	}
	
	
	
}
