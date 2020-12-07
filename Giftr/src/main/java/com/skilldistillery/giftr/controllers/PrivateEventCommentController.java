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

import com.skilldistillery.giftr.entities.PrivateComment;
import com.skilldistillery.giftr.services.PrivateCommentService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RestController
@RequestMapping("api")
public class PrivateEventCommentController {
	

	@Autowired
	private PrivateCommentService pCommentSrv;

	private String username = "22";

	
//	******************* SECURITY API REST POINTS *******************************
	@GetMapping("privateComments")
	public Set<PrivateComment> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		 Set<PrivateComment> pComments = pCommentSrv.index(principal.getName());
		if (pComments == null) {
			res.setStatus(404);
		}
		return pComments;
	}
	
	  @GetMapping("privateComments/{id}")
	  public PrivateComment show(@PathVariable int id, HttpServletResponse res, Principal p){
		  
		  try {
			  PrivateComment privateComment = pCommentSrv.show(p.getName() ,id);
			  res.setStatus(200);
		  } catch (Exception e) {
			  e.printStackTrace();
			  res.setStatus(404);
		  }
		  return pCommentSrv.show(p.getName(),id);
	  }
	  
	  
		@PostMapping("privateComments")
		public PrivateComment newPrivateComment(@RequestBody PrivateComment privateComment, HttpServletRequest req, HttpServletResponse res, Principal p){
			
			try {
				privateComment = pCommentSrv.create(p.getName(), privateComment);
				res.setStatus(201);
				res.setHeader("Location", "api/privateComments/" + privateComment.getId());
			} catch (Exception e) {
				res.setStatus(400);
			}
			return privateComment;
		}
	
		
		
		
		
		@PutMapping("privateComments/{id}")
		public PrivateComment updatePEventComment(@PathVariable int id, @RequestBody PrivateComment privateComment, HttpServletRequest request, HttpServletResponse response, Principal p){
			System.err.println(privateComment);
			
			
			try {
				privateComment = pCommentSrv.update(p.getName(), id, privateComment);
				response.setStatus(201);
				response.setHeader("Location", "api/privateComments/" + privateComment.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			System.err.println(privateComment);
			return privateComment;
		}		
		
		
		
		@DeleteMapping("privateComments/{id}")
		public void deletePEventComment(@PathVariable int id, HttpServletRequest req, HttpServletResponse res, Principal p) {
			try {
				boolean delete = pCommentSrv.destroy(p.getName(), id);
				if(delete) {
					res.setStatus(204);}
				else {
					res.setStatus(404);
				}
				res.setHeader("Location", "api/privateComments/");
			} catch (Exception e) {
				res.setStatus(400);
			}
			
		}
	
	
//	@GetMapping("privateComments")
//	public Set<PrivateComment> index() {
//		return pCommentSrv.index(username);
//	}
//
//	@GetMapping("privateComments/{id}")
//	public PrivateComment show(@PathVariable int id, HttpServletResponse response) {
//
//		try {
//			PrivateComment pComment = pCommentSrv.show(username, id);
//			response.setStatus(200);
//			if(pComment == null ) {
//				response.setStatus(404);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setStatus(404);
//		}
//		return pCommentSrv.show(username, id);
//	}
//
//	@PostMapping("privateComments")
//	public PrivateComment create(@RequestBody PrivateComment pComment, HttpServletRequest req,
//			HttpServletResponse res) {
//		pComment = pCommentSrv.create(username, pComment);
//		if (pComment == null) {
//			res.setStatus(404);
//		} else {
//			res.setStatus(201);
//			StringBuffer url = req.getRequestURL();
//			url.append("/").append(pComment.getId());
//			req.setAttribute("Location", url.toString());
//		}
//		return pCommentSrv.create(username, pComment);
////			return pCommentSrv.create(principal.getName(), pComment);
//	}
//
//	@PutMapping("privateComments/{id}")
//	public PrivateComment update(@PathVariable int id, @RequestBody PrivateComment pComment, HttpServletRequest req,
//			HttpServletResponse res) {
//		try {
//			pComment = pCommentSrv.update(username, id, pComment);
//			if (pComment == null) {
//				res.setStatus(404);
//				pComment = null;
//			}
//			res.setStatus(201);
//			res.setHeader("Location", "api/eventPosts/" + pComment.getId());
//		} catch (Exception e) {
//			res.setStatus(400);
//		}
//		System.err.println(pComment);
//		return pComment;
//	}
//	
//	@DeleteMapping("privateComments/{id}")
//	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
//		try {
//			if (pCommentSrv.destroy(username, id)) {
//				res.setStatus(204);
//			} else {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//		}
//	}
}
