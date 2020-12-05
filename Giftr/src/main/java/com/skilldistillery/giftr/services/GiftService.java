package com.skilldistillery.giftr.services;

import java.util.List;

import com.skilldistillery.giftr.entities.Gift;

public interface GiftService {

	public List<Gift> index();
	
	public Gift findById(Integer giftId);
	
	public Gift createGift(Gift gift);
	
	public Gift updateGift(Integer giftId, Gift gift);
	
	public boolean destory(Integer giftId);
}
