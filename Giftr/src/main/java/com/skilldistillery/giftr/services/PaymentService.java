package com.skilldistillery.giftr.services;

import java.util.Set;

import com.skilldistillery.giftr.entities.Payment;

public interface PaymentService {

	Set<Payment> index(String username);

	Payment show(String username, int id);

	Payment create(String username, Payment payment);

	Payment update(String username, int id, Payment payment);

	boolean destroy(String username, int id);

}
