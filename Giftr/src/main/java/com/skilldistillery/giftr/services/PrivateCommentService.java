package com.skilldistillery.giftr.services;

import java.util.Set;

import com.skilldistillery.giftr.entities.PrivateComment;

public interface PrivateCommentService {

	Set<PrivateComment> index(String username);

	PrivateComment show(String username, int id);

	PrivateComment create(String username, PrivateComment event);

	PrivateComment update(String username, int id, PrivateComment event);

	boolean destroy(String username, int id);
}
