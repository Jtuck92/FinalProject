package com.skilldistillery.giftr.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.giftr.entities.Gift;

public interface GiftService {

	public Set<Gift> index(String string);
	
	public Gift findById(String name, Integer giftId);
	
	public Gift createGift(String name, Gift gift);
	
	public Gift updateGift(String name, Integer giftId, Gift gift);
	
	public boolean destroy(String name, Integer giftId);
}
