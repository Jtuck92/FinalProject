package com.skilldistillery.giftr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.EventComment;

public interface EventCommentRepository extends JpaRepository<EventComment, Integer>{
	
	
}
