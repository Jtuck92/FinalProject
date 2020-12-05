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

class PrivateCommentTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private PrivateComment privateComment;

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
		privateComment = em.find(PrivateComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		privateComment = null;
	}

	@Test
	@DisplayName("test Private Comment entity")
	void test() {
		assertNotNull(privateComment);
		assertEquals("Private Giftr Comment", privateComment.getComment());
	}
	@Test
	@DisplayName("test Entity to User")
	void test1() {
		assertNotNull(privateComment);
		assertEquals("22", privateComment.getUser().getUsername());
	}
	@Test
	@DisplayName("test Entity to Private Post")
	void test2() {
		assertNotNull(privateComment);
		assertEquals("Private Giftr Description", privateComment.getPost().getDescription());
	}
	
}



