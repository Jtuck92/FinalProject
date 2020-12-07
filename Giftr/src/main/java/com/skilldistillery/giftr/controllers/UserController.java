package com.skilldistillery.giftr.controllers;

import java.security.Principal;
import java.util.List;

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

import com.skilldistillery.giftr.entities.User;
import com.skilldistillery.giftr.services.UserService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class UserController {

	@Autowired
	private UserService uSvc;
	
	private String username = "11";

// ********** SECURITY API REST POINTS *****************
	
	@GetMapping("users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<User> users = uSvc.index(principal.getName());
		if(users == null) {
			res.setStatus(404);
		}
		return users;
	}
	
	@GetMapping("users/{userId}")
	public User findById(@PathVariable Integer userId, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		User user = uSvc.findById(principal.getName(), userId);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
	@PostMapping("users")
	public User create(@RequestBody User user, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		user = uSvc.createUser(principal.getName(), user);
		try {
			if(user == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(user.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			user = null;
		}
		return user;
	}
	
	@PutMapping("users/{userId}")
	public User update(@PathVariable Integer userId, @RequestBody User user, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			user = uSvc.updateUser(principal.getName(), userId, user);
			if(user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			user = null;
		}
		return user;
	}
	
	@DeleteMapping("users/{userId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res,Principal principal, @PathVariable Integer userId) {
		try {
			boolean deleted = uSvc.destroy(principal.getName(), userId);
			if(deleted) {
				res.setStatus(204);
			}
			else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	
//	************ TEST API REST POINTS ************************
	
//	@GetMapping("users")
//	public List<User> index() {
//		return uSvc.index(username);
//	}
//	
//	@GetMapping("users/{userId}")
//	public User findById(@PathVariable Integer userId, HttpServletRequest req, HttpServletResponse res) {
//		User user = uSvc.findById(username, userId);
//		if (user == null) {
//			res.setStatus(404);
//		}
//		return uSvc.findById(username, userId);
//	}
//	
//	@PostMapping("users")
//	public User create(@RequestBody User user, HttpServletRequest req, HttpServletResponse res) {
//		user = uSvc.createUser(username, user);
//		try {
//			if(user == null) {
//				res.setStatus(404);
//			} else {
//				res.setStatus(201);
//				StringBuffer url = req.getRequestURL();
//				url.append("/").append(user.getId());
//				res.setHeader("Location", url.toString());
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//			e.printStackTrace();
//			user = null;
//		}
//		return user;
//	}
//	
//	@PutMapping("users/{userId}")
//	public User update(@PathVariable Integer userId, @RequestBody User user, HttpServletRequest req, HttpServletResponse res) {
//		try {
//			user = uSvc.updateUser(username, userId, user);
//			if(user == null) {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//			user = null;
//		}
//		return user;
//	}
//	
//	@DeleteMapping("users/{userId}")
//	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer userId) {
//		try {
//			boolean deleted = uSvc.destroy(username, userId);
//			if(deleted) {
//				res.setStatus(204);
//			}
//			else {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//			e.printStackTrace();
//		}
//	}
	
}
