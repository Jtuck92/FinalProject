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

import com.skilldistillery.giftr.entities.Gift;
import com.skilldistillery.giftr.services.GiftService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class GiftController {

	@Autowired
	private GiftService gSvc;

//	private String username = "11";

//	************ SECURITY API REST POINTS ************************

	@GetMapping("gifts")
	public List<Gift> index(Principal p) {
		return gSvc.index(p.getName());
	}

	@GetMapping("gifts/{giftId}")
	public Gift findById(@PathVariable Integer giftId, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		Gift gift = gSvc.findById(principal.getName(), giftId);
		if (gift == null) {
			res.setStatus(404);
		}
		return gift;
	}

	@PostMapping("gifts")
	public Gift create(@RequestBody Gift gift, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		gift = gSvc.createGift(principal.getName(), gift);
		try {
			if (gift == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(gift.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			gift = null;
		}
		return gift;
	}

	@PutMapping("gifts/{giftId}")
	public Gift update(@PathVariable Integer giftId, @RequestBody Gift gift, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			gift = gSvc.updateGift(principal.getName(), giftId, gift);
			if (gift == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			gift = null;
		}
		return gift;
	}

	@DeleteMapping("gifts/{giftId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable Integer giftId) {
		try {
			boolean deleted = gSvc.destroy(principal.getName(), giftId);
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}

//	************ TEST API REST POINTS ************************
//	
//	@GetMapping("gifts")
//	public List<Gift> index(HttpServletRequest req, HttpServletResponse res) {
//		return gSvc.index();
//	}
//	
//	@GetMapping("gifts/{giftId}")
//	public Gift findById(@PathVariable Integer giftId, HttpServletRequest req, HttpServletResponse res) {
//		Gift gift = gSvc.findById(username, giftId);
//		if (gift == null) {
//			res.setStatus(404);
//		}
//		return gift;
//	}
//
//	@PostMapping("gifts")
//	public Gift create(@RequestBody Gift gift, HttpServletRequest req, HttpServletResponse res) {
//		gift = gSvc.createGift(username, gift);
//		try {
//			if (gift == null) {
//				res.setStatus(404);
//			} else {
//				res.setStatus(201);
//				StringBuffer url = req.getRequestURL();
//				url.append("/").append(gift.getId());
//				res.setHeader("Location", url.toString());
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//			e.printStackTrace();
//			gift = null;
//		}
//		return gift;
//	}
//
//	@PutMapping("gifts/{giftId}")
//	public Gift update(@PathVariable Integer giftId, @RequestBody Gift gift, HttpServletRequest req,
//			HttpServletResponse res) {
//		try {
//			gift = gSvc.updateGift(username, giftId, gift);
//			if (gift == null) {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//			gift = null;
//		}
//		return gift;
//	}
//
//	@DeleteMapping("gifts/{giftId}")
//	public void destroy(HttpServletRequest req, HttpServletResponse res,
//			@PathVariable Integer giftId) {
//		try {
//			boolean deleted = gSvc.destroy(username, giftId);
//			if (deleted) {
//				res.setStatus(204);
//			} else {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//			e.printStackTrace();
//		}
//	}

}
