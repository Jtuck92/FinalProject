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

import com.skilldistillery.giftr.entities.EventPost;
import com.skilldistillery.giftr.services.EventPostService;

@CrossOrigin({"*", "http://localhost:4210"})
@RestController
@RequestMapping("api")
public class EventPostController {
	@Autowired
	private EventPostService eventPostSvc;
	
	private String username = "11";
	
//	******************* SECURITY API REST POINTS *******************************
	
	
	
	@GetMapping("eventPosts")
	public Set<EventPost> index(Principal p){
		return eventPostSvc.index(p.getName());
	}
	
	  @GetMapping("eventPosts/{id}")
	  public EventPost show(@PathVariable int id, HttpServletResponse response, Principal p){
		  
		  try {
			  EventPost eventPost = eventPostSvc.show(p.getName() ,id);
			  response.setStatus(200);
		  } catch (Exception e) {
			  e.printStackTrace();
			  response.setStatus(404);
		  }
		  
		  
		  return eventPostSvc.show(p.getName(),id);
	  }
	  
	  
		@PostMapping("eventPosts")
		public EventPost addEventPost(@RequestBody EventPost userParam, HttpServletRequest request, HttpServletResponse response, Principal p){
			
			try {
				userParam = eventPostSvc.create(p.getName(), userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/eventPosts/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
	
		
		
		
		
		@PutMapping("eventPosts/{id}")
		public EventPost updateEventPost(@PathVariable int id, @RequestBody EventPost userParam, HttpServletRequest request, HttpServletResponse response, Principal p){
			System.err.println(userParam);
			
			
			try {
				userParam = eventPostSvc.update(p.getName(), id, userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/eventPosts/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			System.err.println(userParam);
			return userParam;
		}		
		
		
		
		@DeleteMapping("eventPosts/{id}")
		public void deleteEventPost(@PathVariable int id, HttpServletRequest request, HttpServletResponse response, Principal p) {
			try {
				boolean delete = eventPostSvc.destroy(p.getName(), id);
				if(delete) {
					response.setStatus(204);}
				else {
					response.setStatus(404);
				}
				response.setHeader("Location", "api/eventPosts/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
//	@GetMapping("eventPosts")
//	public Set<EventPost> index(){
//		return eventPostSvc.index(username);
//	}
//	
//	@GetMapping("eventPosts/{id}")
//	public EventPost show(@PathVariable int id, HttpServletResponse response){
//		
//		try {
//			EventPost eventPost = eventPostSvc.show(username ,id);
//			response.setStatus(200);
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setStatus(404);
//		}
//		
//		
//		return eventPostSvc.show(username,id);
//	}
//	@PostMapping("eventPosts")
//	public EventPost addEventPost(@RequestBody EventPost userParam, HttpServletRequest request, HttpServletResponse response){
//		
//		try {
//			userParam = eventPostSvc.create(username, userParam);
//			response.setStatus(201);
//			response.setHeader("Location", "api/eventPosts/" + userParam.getId());
//		} catch (Exception e) {
//			response.setStatus(400);
//		}
//		return userParam;
//	}
//	@PutMapping("eventPosts/{id}")
//	public EventPost updateEventPost(@PathVariable int id, @RequestBody EventPost userParam, HttpServletRequest request, HttpServletResponse response){
//		
//		
//		try {
//			userParam = eventPostSvc.update(username, id, userParam);
//			response.setStatus(201);
//			response.setHeader("Location", "api/eventPosts/" + userParam.getId());
//		} catch (Exception e) {
//			response.setStatus(400);
//		}
//		return userParam;
//	}
//	@DeleteMapping("eventPosts/{id}")
//	public void deleteEventPost(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
//		try {
//			boolean delete = eventPostSvc.destroy(username, id);
//			if(delete) {
//				response.setStatus(204);}
//			else {
//				response.setStatus(404);
//			}
//			response.setHeader("Location", "api/eventPosts/");
//		} catch (Exception e) {
//			response.setStatus(400);
//		}
//		
//	}
}
