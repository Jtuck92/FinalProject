package com.skilldistillery.giftr.services;

import java.util.Set;

import com.skilldistillery.giftr.entities.EventPost;

public interface EventPostService {

	Set<EventPost> index(String username);

	EventPost show(String username, int id);

	EventPost create(String username, EventPost event);

	EventPost update(String username, int id, EventPost event);

	boolean destroy(String username, int id);

}
