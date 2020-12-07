package com.skilldistillery.giftr.services;

import com.skilldistillery.giftr.entities.User;

public interface AuthService {

	public User register(User user);
	
	public User getUser(String username);
}
