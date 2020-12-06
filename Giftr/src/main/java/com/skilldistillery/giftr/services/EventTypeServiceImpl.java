package com.skilldistillery.giftr.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.Event;
import com.skilldistillery.giftr.entities.EventType;
import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.EventTypeRepository;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class EventTypeServiceImpl implements EventTypeService {
	
	@Autowired
	private EventTypeRepository eTRepo;
	@Autowired
	private UserRepository uRepo;

	@Override
	public Set<EventType> index(String username) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			Set<EventType> activeEventTypes = new HashSet<EventType>();
			List<EventType> all = eTRepo.findAll();
			for (EventType eventType : all) {
				activeEventTypes.add(eventType);
			}	
			return activeEventTypes;
			}
		return null;
	}

	@Override
	public EventType show(String username, int id) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			
		Optional<EventType> eTypeOpt = eTRepo.findById(id);
		EventType eType = null;
		if(eTypeOpt.isPresent()) {
			eType = eTypeOpt.get();
			if(eType.getEnabled() == true) {
				for (Event e : user.getEvents()) {
					if(e.getEventTypes().contains(eType)) {
						return eType;									
				}
		
			}
				}
			}			
		}
		return null;
	}
	

	@Override
	public EventType create(String username, EventType eventType) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			eTRepo.saveAndFlush(eventType);
			uRepo.save(user);
			return eventType;
		}		
		return null;
	}

	@Override
	public EventType update(String username, int id, EventType eventType) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			Optional<EventType> ePOpt = eTRepo.findById(id);
			EventType eType = null;
			if(ePOpt.isPresent()) {
				eType = ePOpt.get();
				if(eType.getEnabled() == true) {
					if(eventType.getName() != null) {eType.setName(eventType.getName());}
					if(eventType.getDescription() != null) {eType.setDescription(eventType.getDescription());}
					if(eventType.getImageUrl() != null) {eType.setImageUrl(eventType.getImageUrl());}
					if(eventType.getEvents() != null) {eType.setEvents(eventType.getEvents());}
					eTRepo.saveAndFlush(eType);
					uRepo.saveAndFlush(user);
					return eType;
					}
			
				}
			}
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean deleted = false;
		Optional<EventType> ePOpt = eTRepo.findById(id);
		User user = uRepo.findByUsername(username);
		EventType eComm = null;
		
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
