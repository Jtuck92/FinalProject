package com.skilldistillery.giftr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.PrivatePost;

public interface PrivateEventPostRepository extends JpaRepository<PrivatePost, Integer> {

	PrivatePost findBySubject(String subject);
}
