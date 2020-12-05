package com.skilldistillery.giftr.services;

import java.util.Set;

import com.skilldistillery.giftr.entities.EventComment;

public interface EventCommentService {

	Set<EventComment> index(String username);

	EventComment show(String username, int id);

	EventComment create(String username, EventComment event);

	EventComment update(String username, int id, EventComment event);

	boolean destroy(String username, int id);

}
