package com.skilldistillery.giftr.services;

import java.util.Set;

import com.skilldistillery.giftr.entities.EventType;

public interface EventTypeService {

	Set<EventType> index(String username);

	EventType show(String username, int id);

	EventType create(String username, EventType eventType);

	EventType update(String username, int id, EventType eventType);

	boolean destroy(String username, int id);

}
