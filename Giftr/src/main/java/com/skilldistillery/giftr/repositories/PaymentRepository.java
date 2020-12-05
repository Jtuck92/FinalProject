package com.skilldistillery.giftr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giftr.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
