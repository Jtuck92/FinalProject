package com.skilldistillery.giftr.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.PrivateComment;

public interface PrivateCommentRepository extends JpaRepository<PrivateComment, Integer> {
	Set<PrivateComment> findByUser_Username(String username);
} 