package com.skilldistillery.giftr.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.giftr.entities.Event;
import com.skilldistillery.giftr.repositories.EventRepository;

public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepo;

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
		Event newEvent = (Event) eventRepo.findByName(name);
		if (event != null) {
			event.setName(name);
			eventRepo.saveAndFlush(event);
		}
		return event;
	}

	@Override
	public Event update(String name, int eid, Event event) {
		Event managedEvent = eventRepo.findById(eid);
		if (managedEvent != null) {
			managedEvent.setName(event.getName());
			managedEvent.setDescription(event.getDescription());
			managedEvent.setStartDate(event.getStartDate());
			managedEvent.setEndDate(event.getEndDate());
			managedEvent.setImageUrl(event.getImageUrl());
			eventRepo.saveAndFlush(managedEvent);
		}
		return managedEvent;
	}

	@Override
	public boolean destroy(String name, int eid) {
		boolean deleted = false;
		Event event = eventRepo.findByName(name);
		if (event != null) {
			eventRepo.delete(event);
			deleted = true;
		}
		return deleted;
	}

}
