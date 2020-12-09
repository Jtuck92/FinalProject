package com.skilldistillery.giftr.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.Address;
import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.AddressRepository;
import com.skilldistillery.giftr.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addressRepo;

	// TODO
	@Override
	public Set<Address> index(String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			Set<Address> allAddresses = new HashSet<Address>();
			List<Address> all = addressRepo.findAll();
			for (Address address : all) {
				allAddresses.add(address);
			}	
			return allAddresses;
			}
		return null;
	}


	
	
	@Override
	public Address show(String username, int aid) {
		Optional<Address> addressOpt = addressRepo.findById(aid);
		Address address = null;
		if (addressOpt.isPresent()) {
			address = addressOpt.get();
		}
		return address;
	}

	@Override
	public Address create(String username, Address address) {
		User newUser = userRepo.findByUsername(username);
		if (newUser != null) {
			if( address.getUsers() == null) {
				List<User> users = new ArrayList<User>();
				address.setUsers(users);
			}
			address.getUsers().add(newUser);
			addressRepo.saveAndFlush(address);
			newUser.setAddress(address);
			userRepo.saveAndFlush(newUser);
			return address;
		}
		return null;
	}

	@Override
	public Address update(String username, int aid, Address address) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Optional<Address> addOPt = addressRepo.findById(aid);
			Address updateAdd = null;
			if(addOPt.isPresent()) {
				updateAdd = addOPt.get();
				if(updateAdd.getEnabled() == true) {
					if(updateAdd.getStreet() != null) {updateAdd.setStreet(address.getStreet());}
					if(updateAdd.getStreet2() != null) {updateAdd.setStreet2(address.getStreet2());}
					if(updateAdd.getCity() != null) {updateAdd.setCity(address.getCity());}
					if(updateAdd.getCountry() != null) {updateAdd.setCountry(address.getCountry());}
					if(updateAdd.getZip() != null) {updateAdd.setZip(address.getZip());}
					if(updateAdd.getStateProvince() != null) {updateAdd.setStateProvince(address.getStateProvince());}
					addressRepo.saveAndFlush(updateAdd);
					userRepo.saveAndFlush(user);
					return updateAdd;
					}
			
				}
			}
		return null;
	}
	@Override
	public boolean destroy(String username, int aid) {
		boolean deleted = false;
		Optional<Address> addressEnable = addressRepo.findById(aid);
		Address address = null;
		if (addressEnable.isPresent()) {
			address = addressEnable.get();
			address.setEnabled(false);
			addressRepo.saveAndFlush(address);
			deleted = true;
		}
		return deleted;
	}

}
