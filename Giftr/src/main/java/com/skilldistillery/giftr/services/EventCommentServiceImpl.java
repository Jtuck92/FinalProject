package com.skilldistillery.giftr.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.EventComment;
import com.skilldistillery.giftr.entities.User;
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
		User user = uRepo.findByUsername(username);
		if(user != null) {
			Set<EventComment> allEventComments = new HashSet<EventComment>();
			List<EventComment> all = eCRepo.findAll();
			for (EventComment eventComment : all) {
				allEventComments.add(eventComment);
			}	
			return allEventComments;
			}
		return null;
	}

	@Override
	public EventComment show(String username, int id) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			
		Optional<EventComment> ePOpt = eCRepo.findById(id);
		EventComment eComm = null;
		if(ePOpt.isPresent()) {
			eComm = ePOpt.get();
			if(eComm.isEnabled()) {
			if (user.getEventComments().contains(eComm)) {
					return eComm;					
			}
				}
			}			
		}
		return null;
	}
	

	@Override
	public EventComment create(String username, EventComment eventComment) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			eCRepo.saveAndFlush(eventComment);
			user.getEventComments().add(eventComment);
			uRepo.save(user);
			return eventComment;
		}		
		return null;
	}

	@Override
	public EventComment update(String username, int id, EventComment eventComment) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			Optional<EventComment> ePOpt = eCRepo.findById(id);
			EventComment eComm = null;
			if(ePOpt.isPresent()) {
				eComm = ePOpt.get();
				if(eComm.isEnabled()) {
					if(eComm.getComment() != null) {eComm.setComment(eventComment.getComment());}
					if(eComm.getPost() != null) {eComm.setPost(eventComment.getPost());}
					if(eComm.getUser() != null) {eComm.setUser(eventComment.getUser());}
					eCRepo.saveAndFlush(eComm);
					uRepo.saveAndFlush(user);
					System.err.println(eComm);
					return eComm;
					}
			
				}
			}
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean deleted = false;
		Optional<EventComment> ePOpt = eCRepo.findById(id);
		User user = uRepo.findByUsername(username);
		EventComment eComm = null;
		
		if (user != null) {
			if (ePOpt.isPresent()) {
				eComm = ePOpt.get();
				eComm.setEnabled(deleted);
				uRepo.save(user);
			deleted = true;
			return deleted;
		}
		}
		return deleted;
	}


}
