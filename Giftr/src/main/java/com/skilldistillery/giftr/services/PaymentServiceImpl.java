package com.skilldistillery.giftr.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giftr.entities.Payment;
import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.repositories.PaymentRepository;
import com.skilldistillery.giftr.repositories.UserRepository;
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository pRepo;
	@Autowired
	private UserRepository uRepo;
	
	@Override
	public Set<Payment> index(String username) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			Set<Payment> allPayments = new HashSet<Payment>();
			List<Payment> all = pRepo.findAll();
			for (Payment payment : all) {
				allPayments.add(payment);
			}	
			return allPayments;
			}
		return null;
	}

	@Override
	public Payment show(String username, int id) {
		User user = uRepo.findByUsername(username);
		if(user != null) {
			
		Optional<Payment> pOpt = pRepo.findById(id);
		Payment payment = null;
		if(pOpt.isPresent()) {
			payment = pOpt.get();
			if(payment.getEnabled() == true) {
			if (user.getPayments().contains(payment)) {
					return payment;					
			}
				}
			}			
		}
		return null;
	}

	@Override
	public Payment create(String username, Payment payment) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			pRepo.saveAndFlush(payment);
			user.getPayments().add(payment);
			uRepo.save(user);
			return payment;
		}		
		return null;
	}

	@Override
	public Payment update(String username, int id, Payment payment) {
		User user = uRepo.findByUsername(username);
		if (user != null) {
			Optional<Payment> ePOpt = pRepo.findById(id);
			Payment updatePayment = null;
			if(ePOpt.isPresent()) {
				updatePayment = ePOpt.get();
				if(updatePayment.getEnabled() == true) {
					if(updatePayment.getCardNumber() != null) {updatePayment.setCardNumber(payment.getCardNumber());}
					if(updatePayment.getAmount() != null) {updatePayment.setAmount(payment.getAmount());}
					if(updatePayment.getExp() != null) {updatePayment.setExp(payment.getExp());}
					if(updatePayment.getAddress() != null) {updatePayment.setAddress(payment.getAddress());}
					if(updatePayment.getUser() != null) {updatePayment.setUser(payment.getUser());}
					pRepo.saveAndFlush(updatePayment);
					uRepo.saveAndFlush(user);
					return updatePayment;
					}
			
				}
			}
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean deleted = false;
		Optional<Payment> pOpt = pRepo.findById(id);
		User user = uRepo.findByUsername(username);
		Payment payment = null;
		
		if (user != null) {
			if (pOpt.isPresent()) {
				payment = pOpt.get();
				if(user.getPayments().contains(payment)) {
				payment.setEnabled(deleted);
				uRepo.save(user);
			deleted = true;
			return deleted;
		}
			}
		}
		return deleted;
	}

}
