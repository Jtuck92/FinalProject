package com.skilldistillery.giftr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.Event;
import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.EventRepository;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private UserRepository uRepo;

	@Override
	public List<Event> index(String name) {
		if (eventRepo.findAll() == null) {
			return null;
		}
		return eventRepo.findAll();
	}

	@Override
	public Event show(String name, int eid) {
		Optional<Event> eventOpt = eventRepo.findById(eid);
		Event event = null;
		if (eventOpt.isPresent()) {
			event = eventOpt.get();
		}
		return event;
	}

	@Override
	public Event create(String username, Event event) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			event.setEventManager(user);
			eventRepo.saveAndFlush(event);
			user.getEvents().add(event);
			uRepo.saveAndFlush(user);
			return event;

		}
		return null;
	}

	@Override
	public Event update(String username, int eid, Event event) {
		Optional<Event> managedEvent = eventRepo.findById(eid);
		Event e = null;
		if (managedEvent.isPresent()) {
			e = managedEvent.get();
			if(event.getName() != null) {
				e.setName(event.getName());
			}
			if(event.getDescription() != null) {
				e.setDescription(event.getDescription());
			}
			if(event.getEnabled() != null) {
				e.setEnabled(event.getEnabled());
			}
			if(event.getBudget() != null) {
				e.setBudget(event.getBudget());
			}
			if(event.getImageUrl() != null) {
				e.setImageUrl(event.getImageUrl());
			}
			if(event.getEventManager() != null) {
				e.setEventManager(event.getEventManager());
			}
			if(event.getEventTypes() != null) {
				e.setEventTypes(event.getEventTypes());
			}
			if(event.getUsers() != null) {
				e.setUsers(event.getUsers());
			}
			if(event.getPosts() != null) {
				e.setPosts(event.getPosts());
			}
			if(event.getGifts() != null) {
				e.setGifts(event.getGifts());
			}
			if(event.getStartDate() != null) {
				e.setStartDate(event.getStartDate());
			}
			if(event.getEndDate() != null) {
				e.setEndDate(event.getEndDate());
			}
			
			
			
			
			
			eventRepo.saveAndFlush(e);
		}
		return e;
	}

	@Override
	public boolean disable(String username, int eid) {
		boolean disabled = false;
		Optional<Event> event = eventRepo.findById(eid);
		Event e = null;
		if (event.isPresent()) {
			e = event.get();
			e.setEnabled(false);
			eventRepo.saveAndFlush(e);
			disabled = true;
		}
		return disabled;
	}

}
