package com.skilldistillery.giftr.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.PrivateEvent;

public interface PrivateEventRepository extends JpaRepository<PrivateEvent, Integer> {
	Set<PrivateEvent> findByName(String name);

}
