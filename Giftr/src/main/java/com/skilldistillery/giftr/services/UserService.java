package com.skilldistillery.giftr.services;

import java.util.List;

import com.skilldistillery.giftr.entities.User;

public interface UserService {

	public List<User> index(String username);
	
	public User findById(String username, Integer userId);
	
	public User createUser(String username, User user);
	
	public User updateUser(String username, Integer userId, User user);
	
	public boolean destroy(String username, Integer userId);
	
}
