package com.skilldistillery.giftr.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.EventComment;
import com.skilldistillery.giftr.repositories.EventCommentRepository;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class EventCommentServiceImpl implements EventCommentService {
	
	@Autowired
	private EventCommentRepository eCRepo;
	@Autowired
	private UserRepository uRepo;

	@Override
	public Set<EventComment> index(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventComment show(String username, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventComment create(String username, EventComment event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventComment update(String username, int id, EventComment event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
