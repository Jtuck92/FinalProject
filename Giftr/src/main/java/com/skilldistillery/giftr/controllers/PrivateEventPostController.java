package com.skilldistillery.giftr.controllers;

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

import com.skilldistillery.giftr.entities.PrivatePost;
import com.skilldistillery.giftr.services.PrivateEventPostService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RestController
@RequestMapping("api")
public class PrivateEventPostController {

	@Autowired
	private PrivateEventPostService pPostSvc;

	@GetMapping("privatePosts")
	public Set<PrivatePost> index(String username) {
		return pPostSvc.index(username);
	}

	@GetMapping("privatePosts/{ppId}")
	public PrivatePost show(@PathVariable int ppId, String username, HttpServletResponse response) {
		try {
			PrivatePost pPost = pPostSvc.show(username, ppId);
			response.setStatus(200);
			if (pPost == null) {
				response.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(404);
		}
		return pPostSvc.show(username, ppId);
	}

	@PostMapping("privatePosts")
	public PrivatePost create(@RequestBody PrivatePost pPost, String username, HttpServletRequest req, HttpServletResponse res) {
		pPost = pPostSvc.create(username, pPost);
		if(pPost == null) {
			res.setStatus(404);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(pPost.getId());
			req.setAttribute("Location", url.toString());
		}
		return pPostSvc.create(username, pPost);
	}
	
	@PutMapping("privatePosts/{ppId}")
	public PrivatePost update(@PathVariable int ppId, @RequestBody PrivatePost pPost, String username, HttpServletRequest req,
			HttpServletResponse res) {
		try {
			pPost = pPostSvc.update(username, ppId, pPost);
			if (pPost == null) {
				res.setStatus(404);
				pPost = null;
			}
			res.setStatus(201);
			res.setHeader("Location", "api/eventPosts/" + pPost.getId());
		} catch (Exception e) {
			res.setStatus(400);
		}
		System.err.println(pPost);
		return pPost;
	}
	
	@DeleteMapping("privatePosts/{ppId}")
	public void destory(HttpServletRequest req, HttpServletResponse res,@RequestBody String username, @PathVariable int ppId) {
		try {
			if (pPostSvc.destroy(username, ppId)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}

}
