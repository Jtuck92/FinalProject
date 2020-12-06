package com.skilldistillery.giftr.services;

import java.util.List;

import com.skilldistillery.giftr.entities.Gift;

public interface GiftService {

	public List<Gift> index();
	
	public Gift findById(String name, Integer giftId);
	
	public Gift createGift(String name, Gift gift);
	
	public Gift updateGift(String name, Integer giftId, Gift gift);
	
	public boolean destroy(String name, Integer giftId);
}
