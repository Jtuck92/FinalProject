package com.skilldistillery.giftr.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventCommentTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private EventComment eComm;
	
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
		eComm = em.find(EventComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		eComm = null;
	}

	@Test
	void test() {
		assertNotNull(eComm);
		assertEquals("THIS WAS SUPER FUN", eComm.getComment());
	}
	@Test
	@DisplayName("Test EventComment to Event Post")
	void test1() {
		assertNotNull(eComm);
		assertNotNull(eComm.getPost());
		assertEquals("New Event Description", eComm.getPost().getDescription());
		assertEquals("5", eComm.getPost().getRating());
	}
	@Test
	@DisplayName("Test EventComment to User")
	void test2() {
		assertNotNull(eComm);
		assertNotNull(eComm.getUser());
		assertEquals("22", eComm.getUser().getUsername());
	}

}
