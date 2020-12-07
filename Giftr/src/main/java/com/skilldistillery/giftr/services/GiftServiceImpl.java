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
		Optional<Gift> giftOpt = gRepo.findById(giftId);
		Gift updatedGift = null;
		if(giftOpt.isPresent()) {
			updatedGift = giftOpt.get();
			if(gift.getPrice() != null) {
				updatedGift.setPrice(gift.getPrice());
			}
			if(gift.getWeight() != null) {
				updatedGift.setWeight(gift.getWeight());
			}
			if(gift.getDescription() != null) {
				updatedGift.setDescription(gift.getDescription());
			}
			if(gift.getRating() != 0 ) {
				updatedGift.setRating(gift.getRating());
			}
			if(gift.getName() != null) {
				updatedGift.setName(gift.getName());
			}
			if(gift.getImageUrl() != null) {
				updatedGift.setImageUrl(gift.getImageUrl());
			}
		gRepo.flush();
		}
		return updatedGift;
		
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
