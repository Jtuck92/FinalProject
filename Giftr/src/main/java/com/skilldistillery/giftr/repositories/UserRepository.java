package com.skilldistillery.giftr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}
