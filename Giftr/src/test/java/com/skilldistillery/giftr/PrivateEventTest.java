package com.skilldistillery.giftr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.giftr.entities.PrivateEvent;
import com.skilldistillery.giftr.repositories.PrivateEventRepository;

@SpringBootTest
class PrivateEventTest {

	@Autowired
	private PrivateEventRepository pRepo;
	@BeforeEach
	void setUp() throws Exception {
	}
	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	void testFindByID() {
		Optional<PrivateEvent> eventOpt = pRepo.findById(1);
		assertTrue(eventOpt.isPresent());
		PrivateEvent pEvent = eventOpt.get();
		assertEquals("Private Event Name", pEvent.getName());
	}
}
