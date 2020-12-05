package com.skilldistillery.giftr.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	public Set<Event> index(String name) {
		if (eventRepo.findByName(name) == null) {
			return null;
		}
		return eventRepo.findByName(name);
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
	public Event create(String name, Event event) {
		User loggedInUser = uRepo.findByUsername(name);
		if(loggedInUser != null) {
		List<Event> userEvents = loggedInUser.getEvents();
		eventRepo.saveAndFlush(event);
		userEvents.add(event);
		uRepo.saveAndFlush(loggedInUser);
		return event;
		
		}
		return null;
	}

	@Override
	public Event update(String name, int eid, Event event) {
		Optional<Event> managedEvent = eventRepo.findById(eid);
		Event e = null;
		if(managedEvent.isPresent()) {
			e = managedEvent.get();		
			e.setName(event.getName());
			e.setDescription(event.getDescription());
			e.setStartDate(event.getStartDate());
			e.setEndDate(event.getEndDate());
			e.setImageUrl(event.getImageUrl());
			eventRepo.saveAndFlush(e);
		}
		return e;
	}

	@Override
	public boolean disable(String name, int eid) {
		boolean disabled = false;
		Optional<Event> event = eventRepo.findById(eid);
		Event e = null;
		if(event.isPresent()) {
			e = event.get();
			e.setEnabled(false);
			eventRepo.saveAndFlush(e);
			disabled = true;
		}
		return disabled;
	}

}
