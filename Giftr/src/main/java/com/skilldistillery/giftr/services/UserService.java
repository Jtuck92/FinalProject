package com.skilldistillery.giftr.services;

import java.util.List;

import com.skilldistillery.giftr.entities.User;

public interface UserService {

	public List<User> index();
	
	public User findById(Integer userId);
	
	public User createUser(User user);
	
	public User updateUser(Integer userId, User user);
	
	public boolean destroy(Integer userId);
	
}
