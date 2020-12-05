package com.skilldistillery.giftr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.Gift;

public interface GiftRepository extends JpaRepository<Gift, Integer>{

}
