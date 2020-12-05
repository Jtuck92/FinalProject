package com.skilldistillery.giftr.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giftr.entities.Payment;
import com.skilldistillery.giftr.services.PaymentService;

@CrossOrigin({"*", "http://localhost:4210"})
@RestController
@RequestMapping("api")
public class PaymentController {
	@Autowired
	private PaymentService paymentSvc;
	
	private String username = "11";
	
	
//	************ SECURITY API REST POINTS ************************
//	
//	  @GetMapping("payments")
//	  public Set<Payment> index(Principal p){
//	    return paymentSvc.index(p.getName());
//	  }
//	  
//	  @GetMapping("payments/{id}")
//	  public Payment show(@PathVariable int id, HttpServletResponse response, Principal p){
//		
//		try {
//			Payment payment = paymentSvc.show(p.getName() ,id);
//			response.setStatus(200);
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setStatus(404);
//		}
//			  
//		  
//		return paymentSvc.show(p.getName(),id);
//		  }
//	  
//	  
//		@PostMapping("payments")
//		public Payment addPayment(@RequestBody Payment userParam, HttpServletRequest request, HttpServletResponse response, Principal p){
//			
//			try {
//				userParam = paymentSvc.create(p.getName(), userParam);
//				response.setStatus(201);
//				response.setHeader("Location", "api/payments/" + userParam.getId());
//			} catch (Exception e) {
//				response.setStatus(400);
//			}
//		  return userParam;
//		}
//	
//		@PutMapping("payments/{id}")
//		public Payment updatePayment(@PathVariable int id, @RequestBody Payment userParam, HttpServletRequest request, HttpServletResponse response, Principal p){
//			System.err.println(userParam);
//			
//			
//			try {
//				userParam = paymentSvc.update(p.getName(), id, userParam);
//				response.setStatus(201);
//				response.setHeader("Location", "api/payments/" + userParam.getId());
//			} catch (Exception e) {
//				response.setStatus(400);
//			}
//			System.err.println(userParam);
//			return userParam;
//		}
//		@DeleteMapping("payments/{id}")
//		public void deletePayment(@PathVariable int id, HttpServletRequest request, HttpServletResponse response, Principal p) {
//			try {
//				boolean delete = paymentSvc.destroy(p.getName(), id);
//				if(delete) {
//				response.setStatus(204);}
//				else {
//				  response.setStatus(404);
//				}
//				response.setHeader("Location", "api/payments/");
//			} catch (Exception e) {
//				response.setStatus(400);
//			}
//			
//		}
//	
//	************ TEST API REST POINTS ************************
		@GetMapping("payments")
		public Set<Payment> index(){
			return paymentSvc.index(username);
		}
		
		@GetMapping("payments/{id}")
		public Payment show(@PathVariable int id, HttpServletResponse response){
			
			try {
				Payment payment = paymentSvc.show(username ,id);
				response.setStatus(200);
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(404);
			}
			
			
			return paymentSvc.show(username,id);
		}
		
		
		@PostMapping("payments")
		public Payment addPayment(@RequestBody Payment userParam, HttpServletRequest request, HttpServletResponse response){
			
			try {
				userParam = paymentSvc.create(username, userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/payments/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		
		@PutMapping("payments/{id}")
		public Payment updatePayment(@PathVariable int id, @RequestBody Payment userParam, HttpServletRequest request, HttpServletResponse response){
			System.err.println(userParam);
			
			
			try {
				userParam = paymentSvc.update(username, id, userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/payments/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			System.err.println(userParam);
			return userParam;
		}
		@DeleteMapping("payments/{id}")
		public void deletePayment(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
			try {
				boolean delete = paymentSvc.destroy(username, id);
				if(delete) {
					response.setStatus(204);}
				else {
					response.setStatus(404);
				}
				response.setHeader("Location", "api/payments/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
}
