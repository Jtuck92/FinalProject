package com.skilldistillery.giftr.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	Set<Address> findByUser_Username(String username);
}
