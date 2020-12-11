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

import com.skilldistillery.giftr.entities.EventType;
import com.skilldistillery.giftr.services.EventTypeService;

@CrossOrigin({"*", "http://localhost:4210"})
@RestController
@RequestMapping("api")
public class EventTypeController {
	@Autowired
	private EventTypeService eventTypeSvc;
	
//	private String username = "11";
	
	
//	************ SECURITY API REST POINTS ************************
//	
	  @GetMapping("eventTypes")
	  public Set<EventType> index(Principal p){
	    return eventTypeSvc.index(p.getName());
	  }
	  
	  
	  @GetMapping("eventTypes/{id}")
	  public EventType show(@PathVariable int id, HttpServletResponse response, Principal p){
		
		try {
			EventType eventType = eventTypeSvc.show(p.getName() ,id);
			response.setStatus(200);
			  if(eventType == null) {
				  response.setStatus(400);
				  
			  }
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(404);
		}
			  
		  
		return eventTypeSvc.show(p.getName(),id);
		  }
	  
	  
		@PostMapping("eventTypes")
		public EventType addEventType(@RequestBody EventType userParam, HttpServletRequest request, HttpServletResponse response, Principal p){
			
			try {
				userParam = eventTypeSvc.create(p.getName(), userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/eventTypes/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
		  return userParam;
		}
	
		@PutMapping("eventTypes/{id}")
		public EventType updateEventType(@PathVariable int id, @RequestBody EventType userParam, HttpServletRequest request, HttpServletResponse response, Principal p){
			System.err.println(userParam);
			
			
			try {
				userParam = eventTypeSvc.update(p.getName(), id, userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/eventTypes/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			System.err.println(userParam);
			return userParam;
		}
		@DeleteMapping("eventTypes/{id}")
		public void deleteEventType(@PathVariable int id, HttpServletRequest request, HttpServletResponse response, Principal p) {
			try {
				boolean delete = eventTypeSvc.destroy(p.getName(), id);
				if(delete) {
				response.setStatus(204);}
				else {
				  response.setStatus(404);
				}
				response.setHeader("Location", "api/eventTypes/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
//	
//	************ TEST API REST POINTS ************************
//		@GetMapping("eventTypes")
//		public Set<EventType> index(){
//			return eventTypeSvc.index(username);
//		}
//		
//		@GetMapping("eventTypes/{id}")
//		public EventType show(@PathVariable int id, HttpServletResponse response){
//			
//			try {
//				EventType eventType = eventTypeSvc.show(username ,id);
//				response.setStatus(200);
//			} catch (Exception e) {
//				e.printStackTrace();
//				response.setStatus(404);
//			}
//			
//			
//			return eventTypeSvc.show(username,id);
//		}
//		
//		
//		@PostMapping("eventTypes")
//		public EventType addEventType(@RequestBody EventType userParam, HttpServletRequest request, HttpServletResponse response){
//			
//			try {
//				userParam = eventTypeSvc.create(username, userParam);
//				response.setStatus(201);
//				response.setHeader("Location", "api/eventTypes/" + userParam.getId());
//			} catch (Exception e) {
//				response.setStatus(400);
//			}
//			return userParam;
//		}
//		
//		@PutMapping("eventTypes/{id}")
//		public EventType updateEventType(@PathVariable int id, @RequestBody EventType userParam, HttpServletRequest request, HttpServletResponse response){
//			System.err.println(userParam);
//			
//			
//			try {
//				userParam = eventTypeSvc.update(username, id, userParam);
//				response.setStatus(201);
//				response.setHeader("Location", "api/eventTypes/" + userParam.getId());
//			} catch (Exception e) {
//				response.setStatus(400);
//			}
//			System.err.println(userParam);
//			return userParam;
//		}
//		@DeleteMapping("eventTypes/{id}")
//		public void deleteEventType(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
//			try {
//				boolean delete = eventTypeSvc.destroy(username, id);
//				if(delete) {
//					response.setStatus(204);}
//				else {
//					response.setStatus(404);
//				}
//				response.setHeader("Location", "api/eventTypes/");
//			} catch (Exception e) {
//				response.setStatus(400);
//			}
//			
//		}
	
}
