package com.skilldistillery.giftr.services;

import java.util.Set;

import com.skilldistillery.giftr.entities.PrivatePost;

public interface PrivateEventPostService {

	Set<PrivatePost> index(String username);

	public PrivatePost show(String username, int ppid);

	public PrivatePost create(String username, PrivatePost post);

	public PrivatePost update(String username, int ppid, PrivatePost post);

	boolean destroy(String username, int ppid);
}
