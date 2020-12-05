package com.skilldistillery.giftr.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.EventPost;
import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.EventPostRepository;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class EventPostServiceImpl implements EventPostService {
	
	@Autowired
	private EventPostRepository ePRepo;
	@Autowired
	private UserRepository uRepo;

	@Override
	public Set<EventPost> index(String username) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			Set<EventPost> activeEventPosts = new HashSet<EventPost>();
			List<EventPost> all = ePRepo.findAll();
			for (EventPost eventPost : all) {
				activeEventPosts.add(eventPost);
				return activeEventPosts;
			}	
			}
		return null;
	}

	@Override
	public EventPost show(String username, int id) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			
		Optional<EventPost> ePOpt = ePRepo.findById(id);
		EventPost ep = null;
		if(ePOpt.isPresent()) {
			ep = ePOpt.get();
			if(ep.isEnabled()) {
			if (user.getEventPosts().contains(ep)) {
					return ep;					
			}
				}
			}			
		}
		return null;
	}

	@Override
	public EventPost create(String username, EventPost eventPost) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			ePRepo.saveAndFlush(eventPost);
			user.getEventPosts().add(eventPost);
			uRepo.save(user);
			return eventPost;
		}		
		return null;
	}

	@Override
	public EventPost update(String username, int id, EventPost eventPost) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			Optional<EventPost> ePOpt = ePRepo.findById(id);
			EventPost ep = null;
			if(ePOpt.isPresent()) {
				ep = ePOpt.get();
				if(ep.isEnabled()) {
					if(ep.getDescription() != null) {ep.setDescription(eventPost.getDescription());}
					if(ep.getImageUrl() != null) {ep.setImageUrl(eventPost.getImageUrl());}
					if(ep.getRating() != null) {ep.setRating(eventPost.getRating());}
					if(ep.getSubject() != null) {ep.setSubject(eventPost.getSubject());}
					if(ep.getEvent() != null) {ep.setEvent(eventPost.getEvent());}
					if(ep.getUser() != null) {ep.setUser(eventPost.getUser());}
					if(ep.getComments() != null) {ep.setComments(eventPost.getComments());}
					ePRepo.saveAndFlush(ep);
					uRepo.saveAndFlush(user);
					}
				}
			}
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean deleted = false;
		Optional<EventPost> ePOpt = ePRepo.findById(id);
		User user = uRepo.findByUsername(username);
		EventPost ep = null;
		
		if (user != null) {
			if (ePOpt.isPresent()) {
				ep = ePOpt.get();
				ep.setEnabled(deleted);
				uRepo.save(user);
			deleted = true;
			return deleted;
		}
		}
		return deleted;
	}


}
