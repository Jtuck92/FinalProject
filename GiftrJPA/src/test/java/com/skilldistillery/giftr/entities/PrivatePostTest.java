package com.skilldistillery.giftr.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PrivatePostTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private PrivatePost privatePost;

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
		privatePost = em.find(PrivatePost.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		privatePost = null;
	}

	@Test
	void test() {
		assertNotNull(privatePost);
		assertEquals("Private Giftr Description", privatePost.getDescription());

	}
	@Test
	@DisplayName("Test Private Post to User")
	void test1() {
		assertNotNull(privatePost);
		assertNotNull(privatePost.getUser());
		assertEquals("11", privatePost.getUser().getUsername());
		
	}
	@Test
	@DisplayName("Test Private Post to Private Event")
	void test3() {
		assertNotNull(privatePost);
		assertNotNull(privatePost.getPrvEvent());
		assertEquals("Private Event Name", privatePost.getPrvEvent().getName());
		
	}
	@Test
	@DisplayName("Test Private Post to PrivateComment")
	void test2() {
		assertNotNull(privatePost);
		assertNotNull(privatePost.getPrvComments());
		assertTrue(privatePost.getPrvComments().size()>0);
		assertEquals("Private Giftr Comment", privatePost.getPrvComments().get(0).getComment());
		
	}
}
