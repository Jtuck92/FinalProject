package com.skilldistillery.giftr.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{
	Set<Event> findByName(String name);

	Event findById(int id);
}
