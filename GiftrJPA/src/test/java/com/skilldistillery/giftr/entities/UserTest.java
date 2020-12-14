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

public class UserTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("test User entity")
	void test() {
		assertNotNull(user);
		assertEquals("11", user.getUsername());
	}
	@Test
	@DisplayName("test User to Private Event List")
	void test1() {
		assertNotNull(user);
		assertNotNull(user.getPrvEvents());
		assertTrue(user.getPrvEvents().size()>0);
		assertEquals("Private Event Name", user.getPrvEvents().get(0).getName());
	}
	@Test
	@DisplayName("test User to Private Event Manager EventList")
	void test2() {
		assertNotNull(user);
		assertNotNull(user.getManagerPrvEvents());
		assertTrue(user.getManagerPrvEvents().size()>0);
		assertTrue(user.getManagerPrvEvents().get(0).getPrivateEventManager() == user);
		assertEquals("Private Event Name", user.getPrvEvents().get(0).getName());
	}
	
	@Test
	@DisplayName("test User to Private Post")
	void test3() {
		assertNotNull(user);
		assertNotNull(user.getPrvEventPosts());
		assertTrue(user.getPrvEventPosts().size()>0);
		assertEquals("Private Giftr Description", user.getPrvEventPosts().get(0).getDescription());
	}
	@Test
	@DisplayName("test User to Private Comment")
	void test4() {
		User user2 = em.find(User.class, 2);
		assertNotNull(user2);
		assertNotNull(user2.getPrvEventComments());
		assertTrue(user2.getPrvEventComments().size()>0);
		assertEquals("Private Giftr Comment", user2.getPrvEventComments().get(0).getComment());
	}
	@Test
	@DisplayName("test User to Payment")
	void test5() {
		assertNotNull(user);
		assertNotNull(user.getPayments());
		assertTrue(user.getPayments().size()>0);
		assertEquals("1111111111111111", user.getPayments().get(0).getCardNumber());
		assertEquals(11.11, user.getPayments().get(0).getAmount());
	}
	@Test
	@DisplayName("test User to Address")
	void test6() {
		assertNotNull(user);
		assertNotNull(user.getAddress());
		assertEquals("11111", user.getAddress().getZip());
		assertEquals("9303 Lyon Drive", user.getAddress().getStreet());	
		}
	@Test
	@DisplayName("test User to Event Comment")
	void test7() {
		User user2 = em.find(User.class, 2);
		assertNotNull(user2);
		assertNotNull(user2.getEventComments());
		assertTrue(user2.getEventComments().size() > 0);
		assertEquals("Cant Wait for next year!", user2.getEventComments().get(0).getComment());	
	}
	@Test
	@DisplayName("test User to Event Post")
	void test8() {
		assertNotNull(user);
		assertNotNull(user.getEventPosts());
		assertTrue(user.getEventPosts().size() > 0);
		assertEquals("Thank you so much! I love the gifts including the graphic novel (not pictured)", user.getEventPosts().get(0).getDescription());	
	}
	@Test
	@DisplayName("test User to EventList")
	void test9() {
		assertNotNull(user);
		assertNotNull(user.getEvents());
		assertTrue(user.getEvents().size() > 0);
		assertEquals("Secret Santa", user.getEvents().get(0).getName());	
	}
	@Test
	@DisplayName("test User to EventManager EventList")
	void test10() {
		user = em.find(User.class, 4);
		assertNotNull(user);
		assertNotNull(user.getManagerEvents());
		assertTrue(user.getManagerEvents().size() > 0);
		assertTrue(user.getManagerEvents().get(0).getEventManager() == user);
		assertEquals("Secret Santa", user.getManagerEvents().get(0).getName());	
	}
	@Test
	@DisplayName("test User to Gifts Sent")
	void test11() {
		assertNotNull(user);
		assertNotNull(user.getSentGifts());
		assertTrue(user.getSentGifts().size()>0);
		assertEquals(12.5, user.getSentGifts().get(0).getPrice());	
		assertEquals(2, user.getSentGifts().get(0).getReceiver().getId());	
		assertEquals("New Santa Gift", user.getSentGifts().get(0).getDescription());	


	}
	@Test
	@DisplayName("test User to Gifts Sent")
	void test12() {
		User user2 = em.find(User.class, 2);

		assertNotNull(user2);
		assertNotNull(user2.getRecievedGifts());
		assertTrue(user2.getRecievedGifts().size()>0);
		assertEquals(12.5, user2.getRecievedGifts().get(0).getPrice());	
		assertEquals(1, user2.getRecievedGifts().get(0).getGifter().getId());	
		assertEquals("New Santa Gift", user2.getRecievedGifts().get(0).getDescription());	
		
	}
	
	
	
	
}
