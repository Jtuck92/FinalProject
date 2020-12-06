package com.skilldistillery.giftr.services;

import java.util.List;

import com.skilldistillery.giftr.entities.Event;

public interface EventService {

	List<Event> index(String name);

	Event show(String name, int eid);

	Event create(String name, Event event);

	Event update(String name, int tid, Event event);

	boolean disable(String name, int eid);

}
