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

import com.skilldistillery.giftr.entities.EventComment;
import com.skilldistillery.giftr.services.EventCommentService;

@CrossOrigin({"*", "http://localhost:4210"})
@RestController
@RequestMapping("api")
public class EventCommentController {
	@Autowired
	private EventCommentService eventCommentSvc;
	
	private String username = "22";
	
	
//	************ SECURITY API REST POINTS ************************
//	
//	  @GetMapping("eventComments")
//	  public Set<EventComment> index(Principal p){
//	    return eventCommentSvc.index(p.getName());
//	  }
//	  
//	  @GetMapping("eventComments/{id}")
//	  public EventComment show(@PathVariable int id, HttpServletResponse response, Principal p){
//		
//		try {
//			EventComment eventComment = eventCommentSvc.show(p.getName() ,id);
//			response.setStatus(200);
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setStatus(404);
//		}
//			  
//		  
//		return eventCommentSvc.show(p.getName(),id);
//		  }
//	  
//	  
//		@PostMapping("eventComments")
//		public EventComment addEventComment(@RequestBody EventComment userParam, HttpServletRequest request, HttpServletResponse response, Principal p){
//			
//			try {
//				userParam = eventCommentSvc.create(p.getName(), userParam);
//				response.setStatus(201);
//				response.setHeader("Location", "api/eventComments/" + userParam.getId());
//			} catch (Exception e) {
//				response.setStatus(400);
//			}
//		  return userParam;
//		}
//	
//		@PutMapping("eventComments/{id}")
//		public EventComment updateEventComment(@PathVariable int id, @RequestBody EventComment userParam, HttpServletRequest request, HttpServletResponse response, Principal p){
//			System.err.println(userParam);
//			
//			
//			try {
//				userParam = eventCommentSvc.update(p.getName(), id, userParam);
//				response.setStatus(201);
//				response.setHeader("Location", "api/eventComments/" + userParam.getId());
//			} catch (Exception e) {
//				response.setStatus(400);
//			}
//			System.err.println(userParam);
//			return userParam;
//		}
//		@DeleteMapping("eventComments/{id}")
//		public void deleteEventComment(@PathVariable int id, HttpServletRequest request, HttpServletResponse response, Principal p) {
//			try {
//				boolean delete = eventCommentSvc.destroy(p.getName(), id);
//				if(delete) {
//				response.setStatus(204);}
//				else {
//				  response.setStatus(404);
//				}
//				response.setHeader("Location", "api/eventComments/");
//			} catch (Exception e) {
//				response.setStatus(400);
//			}
//			
//		}
//	
//	************ TEST API REST POINTS ************************
		@GetMapping("eventComments")
		public Set<EventComment> index(){
			return eventCommentSvc.index(username);
		}
		
		@GetMapping("eventComments/{id}")
		public EventComment show(@PathVariable int id, HttpServletResponse response){
			
			try {
				EventComment eventComment = eventCommentSvc.show(username ,id);
				response.setStatus(200);
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(404);
			}
			
			
			return eventCommentSvc.show(username,id);
		}
		
		
		@PostMapping("eventComments")
		public EventComment addEventComment(@RequestBody EventComment userParam, HttpServletRequest request, HttpServletResponse response){
			
			try {
				userParam = eventCommentSvc.create(username, userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/eventComments/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		
		@PutMapping("eventComments/{id}")
		public EventComment updateEventComment(@PathVariable int id, @RequestBody EventComment userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = eventCommentSvc.update(username, id, userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/eventComments/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		@DeleteMapping("eventComments/{id}")
		public void deleteEventComment(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
			try {
				boolean delete = eventCommentSvc.destroy(username, id);
				if(delete) {
					response.setStatus(204);}
				else {
					response.setStatus(404);
				}
				response.setHeader("Location", "api/eventComments/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
}
