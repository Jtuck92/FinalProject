package com.skilldistillery.giftr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.Gift;
import com.skilldistillery.giftr.repositories.GiftRepository;

@Service
public class GiftServiceImpl implements GiftService {
	
	@Autowired
	private GiftRepository gRepo;

	@Override
	public List<Gift> index(String username) {
		return gRepo.findAll();
	}

	@Override
	public Gift findById(String name, Integer giftId) {
		Optional<Gift> giftOpt = gRepo.findById(giftId);
		Gift gift = null;
		if(giftOpt.isPresent()) {
			gift = giftOpt.get();
		}
		return gift;
	}

	@Override
	public Gift createGift(String name, Gift gift) {
		gRepo.saveAndFlush(gift);
		return gift;
	}

	@Override
	public Gift updateGift(String name, Integer giftId, Gift gift) {
		System.err.println("GIFT WITH UPDATES" + gift);
		Optional<Gift> giftOpt = gRepo.findById(giftId);
		Gift updatedGift = null;
		if(giftOpt.isPresent()) {
			System.err.println("OPT GET" + giftOpt.get());
			updatedGift = giftOpt.get();
			System.err.println("SET PRICE");
			if(gift.getPrice() != null) {
				System.err.println("SET PRICE");
				updatedGift.setPrice(gift.getPrice());
			}
			System.err.println("SET WEIGHT");
			if(gift.getWeight() != null) {
				System.err.println("SET WEIGHT");
				updatedGift.setWeight(gift.getWeight());
			}
			System.err.println("SET DESCRIPTION");
			if(gift.getDescription() != null) {
				System.err.println("SET DESCRIPTION");
				updatedGift.setDescription(gift.getDescription());
			}
			System.err.println("SET RATING");
			if(gift.getRating() != null ) {
				System.err.println("SET RATING");
				updatedGift.setRating(gift.getRating());
			}
			System.err.println("SET Name");
			if(gift.getName() != null) {
				System.err.println("SET Name");
				updatedGift.setName(gift.getName());
			}
			System.err.println("SET IMAGE");
			if(gift.getImageUrl() != null) {
				System.err.println("SET IMAGE");
				updatedGift.setImageUrl(gift.getImageUrl());
			}
			System.err.println("SET NOTE");
			if(gift.getNote() != null) {
				System.err.println("SET NOTE");
				updatedGift.setNote(gift.getNote());
			}
			System.err.println("SET RECIEVER");
			if(gift.getReceiver() != null) {
				System.err.println("SET RECIEVER");
				updatedGift.setReceiver(gift.getReceiver());
			}
			gRepo.saveAndFlush(updatedGift);
			System.err.println("BEFORE RETURN" + updatedGift);
			return updatedGift;
		}
		return null;
		
	}

	@Override
	public boolean destroy(String name, Integer giftId) {
		boolean deleted = false;
		Optional<Gift> giftOpt = gRepo.findById(giftId);
		Gift gift = null;
		if (giftOpt.isPresent()) {
			gift = giftOpt.get();
			gift.setEnabled(false);
			gRepo.saveAndFlush(gift);
			deleted = true;
		}
		return deleted;
	}

}
