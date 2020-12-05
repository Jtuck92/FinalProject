package com.skilldistillery.giftr.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.giftr.entities.PrivatePost;
import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.PrivateEventPostRepository;
import com.skilldistillery.giftr.repositories.UserRepository;

public class PrivateEventPostServiceImpl implements PrivateEventPostService {

	@Autowired
	private PrivateEventPostRepository ppRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Set<PrivatePost> index(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Set<PrivatePost> activePrivateEventPost = new HashSet<PrivatePost>();
			List<PrivatePost> all = ppRepo.findAll();
			for (PrivatePost pEventPost : all) {
				System.out.println(all.size());
				activePrivateEventPost.add(pEventPost);
			}
			return activePrivateEventPost;
		}
		return null;
	}

	@Override
	public PrivatePost show(String username, int ppid) {
		Optional<PrivatePost> privatePostOpt = ppRepo.findById(ppid);
		PrivatePost privatePost = null;
		if (privatePostOpt.isPresent()) {
			privatePost = privatePostOpt.get();
			//PrivatePost class has get enabled vs is enabled. Check back on this. 
			if (privatePost.getEnabled() != null) {
				User user = userRepo.findByUsername(username);
				if (user.getPrvEventPosts().contains(privatePost)) {
					return privatePost;
				}
			}
		}
		return null;
	}

	@Override
	public PrivatePost create(String username, PrivatePost post) {
		User loggedInUser = userRepo.findByUsername(username);
		if (loggedInUser != null) {
			post.setUser(loggedInUser);
			ppRepo.saveAndFlush(post);
			userRepo.saveAndFlush(loggedInUser);
			return post;
		}
		return null;
	}

	@Override
	public PrivatePost update(String subject, int ppId, PrivatePost post) {
		Optional<PrivatePost> managedPrivatePost = ppRepo.findById(ppId);
		PrivatePost pPost = null;

		if (managedPrivatePost.isPresent()) {
			pPost = managedPrivatePost.get();
			pPost.setSubject(post.getSubject());
			pPost.setDescription(post.getDescription());
			pPost.setImageUrl(post.getImageUrl());
			ppRepo.saveAndFlush(pPost);
		}
		return pPost;
	}

	@Override
	public boolean destroy(String subject, int ppId) {
		boolean deleted = false;
		Optional<PrivatePost> privatePost = ppRepo.findById(ppId);
		PrivatePost pPost = null;
		if (privatePost.isPresent()) {
			pPost = privatePost.get();
			pPost.setEnabled(false);
			ppRepo.saveAndFlush(pPost);
			deleted = true;
		}

		return deleted;
	}

}
