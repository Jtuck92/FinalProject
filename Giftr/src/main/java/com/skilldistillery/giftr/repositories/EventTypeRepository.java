package com.skilldistillery.giftr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.EventType;

public interface EventTypeRepository extends JpaRepository<EventType, Integer> {
}
