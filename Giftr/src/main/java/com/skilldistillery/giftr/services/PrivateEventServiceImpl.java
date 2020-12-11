package com.skilldistillery.giftr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.PrivateEvent;
import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.PrivateEventRepository;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class PrivateEventServiceImpl implements PrivateEventService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PrivateEventRepository privateEventRepo;


	@Override
	public List<PrivateEvent> index(String name) {
		if (privateEventRepo.findByName(name) == null) {
			return null;
		}
		return privateEventRepo.findAll();
	}

	@Override
	public PrivateEvent show(String name, int eid) {
//		Optional<User> userOpt = userRepo.findByUsername(name);
		Optional<PrivateEvent> privateEventOpt = privateEventRepo.findById(eid);
		PrivateEvent privateEvent = null;
		if (privateEventOpt.isPresent()) {
			privateEvent = privateEventOpt.get();
		}
		return privateEvent;
	}

	@Override
	public boolean destroy(String name, int eid) {
		boolean deleted = false;
		Optional<PrivateEvent> privateEvent = privateEventRepo.findById(eid);
		PrivateEvent pEvent = null;
		if (privateEvent.isPresent()) {
			pEvent = privateEvent.get();
			pEvent.setEnabled(false);
			privateEventRepo.saveAndFlush(pEvent);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public PrivateEvent create(String username, PrivateEvent event) {
		User loggedInUser = userRepo.findByUsername(username);
		if (loggedInUser != null) {
			event.setPrivateEventManager(loggedInUser);
			privateEventRepo.saveAndFlush(event);
			userRepo.saveAndFlush(loggedInUser);
			return event;
		}
		return null;
	}

	@Override
	public PrivateEvent update(String name, int eid, PrivateEvent event) {
		Optional<PrivateEvent> managedPrivateEvent = privateEventRepo.findById(eid);
		PrivateEvent pEvent = null;

		if (managedPrivateEvent.isPresent()) {
			pEvent = managedPrivateEvent.get();
			pEvent.setName(event.getName());
			pEvent.setDescription(event.getDescription());
			pEvent.setImageUrl(event.getImageUrl());
			privateEventRepo.saveAndFlush(pEvent);
		}
		return pEvent;
	}

}
