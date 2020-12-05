package com.skilldistillery.giftr.services;

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
		if (addressRepo.findByUser_Username(username) == null) {
			return null;
		}
		return addressRepo.findByUser_Username(username);
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
		Optional<Address> updatedAddress = addressRepo.findById(aid);
		Address updateAddress = null;

		if (updatedAddress.isPresent()) {
			updateAddress = updatedAddress.get();
			updateAddress.setStreet(updateAddress.getStreet());
			updateAddress.setStreet2(updateAddress.getStreet2());
			updateAddress.setCountry(updateAddress.getCountry());
			updateAddress.setZip(updateAddress.getZip());
			updateAddress.setStateProvince(updateAddress.getStateProvince());
			addressRepo.saveAndFlush(updateAddress);
		}
		return updateAddress;
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
