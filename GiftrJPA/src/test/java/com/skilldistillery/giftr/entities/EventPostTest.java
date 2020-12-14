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
		assertEquals("Thank you so much! I love the gifts including the graphic novel (not pictured)", ePost.getDescription());
		assertEquals("4", ePost.getRating());
	}
	
	@Test
	@DisplayName("Test EventPost to User")
	void test_eventPost_user_relationship_mapping() {
		assertNotNull(ePost);
		assertEquals(1, ePost.getId());
	}
	@Test
	@DisplayName("Test EventPost to Event")
	void test3() {
		assertNotNull(ePost);
		assertNotNull(ePost.getEvent());
		assertEquals("Marvel", ePost.getEvent().getName());
	}
	@Test
	@DisplayName("Test EventPost to EventComment")
	void test2() {
		assertNotNull(ePost);
		assertNotNull(ePost.getComments());
		assertTrue(ePost.getComments().size()>0);
		assertEquals("THIS WAS SUPER FUN", ePost.getComments().get(0).getComment());
	}

	
	
	
}
