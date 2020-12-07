package com.skilldistillery.giftr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
//			encrypt and set the password for the User.
		user.setPassword(encoder.encode(user.getPassword()));
//			set the enabled field of the object to true.
		user.setEnabled(true);
//			saveAndFlush the user using the UserRepo.
		userRepo.saveAndFlush(user);
//			return the User object.
		return user;
	}

	@Override
	public User getUser(String username) {

		return userRepo.findByUsername(username);
	}

}