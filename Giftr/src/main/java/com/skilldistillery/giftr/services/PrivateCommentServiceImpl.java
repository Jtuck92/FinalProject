package com.skilldistillery.giftr.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.PrivateComment;
import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.PrivateCommentRepository;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class PrivateCommentServiceImpl implements PrivateCommentService {

	@Autowired
	private PrivateCommentRepository pEventCommentRepo;
	@Autowired
	private UserRepository uRepo;


	@Override
	public Set<PrivateComment> index(String username) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			Set<PrivateComment> activePrivateEventComment = new HashSet<PrivateComment>();
			List<PrivateComment> all = pEventCommentRepo.findAll();
			for (PrivateComment pEventComment : all) {
				System.err.println(all.size());
				activePrivateEventComment.add(pEventComment);
			}
			return activePrivateEventComment;
		}
		return null;
	}

	@Override
	public PrivateComment show(String username, int id) {
		Optional<PrivateComment> pCommentOpt = pEventCommentRepo.findById(id);
		PrivateComment pC = null;
		if (pCommentOpt.isPresent()) {
			pC = pCommentOpt.get();
			if (pC.isEnabled()) {
				User user = uRepo.findByUsername(username);
				if (user.getPrvEventComments().contains(pC)) {
					return pC;
				}
			}
		}
		return null;
	}

	@Override
	public PrivateComment create(String username, PrivateComment event) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			pEventCommentRepo.saveAndFlush(event);
			user.getPrvEventComments().add(event);
			uRepo.save(user);
			return event;
		}
		return null;
	}

	@Override
	public PrivateComment update(String username, int id, PrivateComment event) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			Optional<PrivateComment> pCommentOpt = pEventCommentRepo.findById(id);
			PrivateComment pC = null;
			if (pCommentOpt.isPresent()) {
				pC = pCommentOpt.get();
				if (pC.isEnabled()) {
					if (pC.getComment() != null) {
						pC.setComment(event.getComment());
					}
					pEventCommentRepo.saveAndFlush(pC);
					uRepo.saveAndFlush(user);
				}
			}
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean deleted = false;
		Optional<PrivateComment> pCommentOpt = pEventCommentRepo.findById(id);
		User user = uRepo.findByUsername(username);
		PrivateComment pC = null;
		if (user != null) {
			if (pCommentOpt.isPresent()) {
				pC = pCommentOpt.get();
				pC.setEnabled(deleted);
				uRepo.save(user);
				deleted = true;
				return deleted;
			}
		}
		return deleted;
	}

}
