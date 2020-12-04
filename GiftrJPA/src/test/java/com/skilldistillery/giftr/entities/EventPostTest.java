package com.skilldistillery.giftr.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventPostTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private EventPost ePost;
	
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
		ePost = em.find(EventPost.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		ePost = null;
	}


	@Test
	void test() {
		assertNotNull(ePost);
		assertEquals("New Event Description", ePost.getDescription());
		assertEquals("5", ePost.getRating());
	}

}
