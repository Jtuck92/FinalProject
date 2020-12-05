package com.skilldistillery.giftr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> index(String username) {
		return userRepo.findAll();
	}

	@Override
	public User findById(String username, Integer userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		User user = null;
		if(userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}

	@Override
	public User createUser(String username, User user) {
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User updateUser(String username, Integer userId, User user) {
		Optional<User> userOpt = userRepo.findById(userId);
		User updatedUser = null;
		if (userOpt.isPresent()) {
			updatedUser = userOpt.get();
			if(user.getUsername() != null) {
				updatedUser.setUsername(user.getUsername());
			}
			if(user.getPassword() != null) {
				updatedUser.setPassword(user.getPassword());
			}
			if(user.getEmail() != null) {
				updatedUser.setEmail(user.getEmail());
			}
			if(user.getFirstName() != null) {
				updatedUser.setFirstName(user.getFirstName());
			}
			if(user.getLastName() != null) {
				updatedUser.setLastName(user.getLastName());
			}
		
		userRepo.flush();	
		}
		return updatedUser;
	}

	@Override
	public boolean destroy(String username, Integer userId) {
		boolean deleted = false;
	Optional<User> userToDelete = userRepo.findById(userId);
	User user = null;
	if(userToDelete.isPresent()) {
		user = userToDelete.get();
		user.setEnabled(false);
		userRepo.saveAndFlush(user);
		deleted = true;
	}
	return deleted;
	}
}
