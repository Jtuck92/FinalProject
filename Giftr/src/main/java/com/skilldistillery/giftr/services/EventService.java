package com.skilldistillery.giftr.services;

import java.util.Set;

import com.skilldistillery.giftr.entities.Event;

public interface EventService {

	Set<Event> index(String name);

	Event show(String name, int eid);

	Event create(String name, Event event);

	Event update(String name, int tid, Event event);

	boolean destroy(String name, int eid);

}
