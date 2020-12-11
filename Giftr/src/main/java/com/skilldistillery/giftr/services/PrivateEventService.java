package com.skilldistillery.giftr.services;

import java.util.List;

import com.skilldistillery.giftr.entities.PrivateEvent;

public interface PrivateEventService {

	List<PrivateEvent> index(String name);

	public PrivateEvent show(String name, int eid);

	public PrivateEvent create(String name, PrivateEvent event);

	public PrivateEvent update(String name, int eid, PrivateEvent event);

	boolean destroy(String name, int eid);
}
