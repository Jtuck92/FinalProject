package com.skilldistillery.giftr.services;

import java.util.Set;

import com.skilldistillery.giftr.entities.Address;

public interface AddressService {
	 
	Set<Address> index(String username);

	public Address show(String username, int aid);

	public Address create(String username, Address address);

	public Address update(String username, int aid, Address address);

	boolean destroy(String username, int aid);

}
