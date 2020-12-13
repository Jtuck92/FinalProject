package com.skilldistillery.giftr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder encoder;
	
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
		if (userOpt.isPresent()) {
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
			if (user.getUsername() != null) {
				updatedUser.setUsername(user.getUsername());
			}
			if (user.getPassword() != null) {
				updatedUser.setPassword(encoder.encode(user.getPassword()));
			}
			if (user.getEmail() != null) {
				updatedUser.setEmail(user.getEmail());
			}
			if (user.getFirstName() != null) {
				updatedUser.setFirstName(user.getFirstName());
			}
			if (user.getLastName() != null) {
				updatedUser.setLastName(user.getLastName());
			}
			if (user.getRole() != null) {
				updatedUser.setRole(user.getRole());
			}
			if (user.getGender() != null) {
				updatedUser.setGender(user.getGender());
			}
			if (user.getBirthDate() != null) {
				updatedUser.setBirthDate(user.getBirthDate());
			}
			if (user.getAddress() != null) {
				updatedUser.setAddress(user.getAddress());
			}
			if (user.getEventPosts() != null) {
				updatedUser.setEventPosts(user.getEventPosts());
			}
			if (user.getEventComments() != null) {
				updatedUser.setEventComments(user.getEventComments());
			}

			if (user.getManagerEvents() != null) {
				updatedUser.setManagerEvents(user.getManagerEvents());
			}
			if (user.getSentGifts() != null) {
				updatedUser.setSentGifts(user.getSentGifts());
			}

			if (user.getRecievedGifts() != null) {
				updatedUser.setRecievedGifts(user.getRecievedGifts());
			}
			if (user.getManagerPrvEvents() != null) {
				updatedUser.setManagerPrvEvents(user.getManagerPrvEvents());
			}
			if (user.getPrvEventPosts() != null) {
				updatedUser.setPrvEventPosts(user.getPrvEventPosts());
			}
			if (user.getPrvEventComments() != null) {
				updatedUser.setPrvEventComments(user.getPrvEventComments());
			}
			if (user.getPrvEvents() != null) {
				updatedUser.setPrvEvents(user.getPrvEvents());
			}
			if (user.getPayments() != null) {
				updatedUser.setPayments(user.getPayments());
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
		if (userToDelete.isPresent()) {
			user = userToDelete.get();
			user.setEnabled(false);
			userRepo.saveAndFlush(user);
			deleted = true;
		}
		return deleted;
	}
}
