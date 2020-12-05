package com.skilldistillery.giftr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.EventPost;

public interface EventPostRepository extends JpaRepository<EventPost, Integer>{
	
	
}
