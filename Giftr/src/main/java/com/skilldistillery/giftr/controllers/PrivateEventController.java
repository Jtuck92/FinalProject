package com.skilldistillery.giftr.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giftr.entities.PrivateEvent;
import com.skilldistillery.giftr.services.PrivateEventService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class PrivateEventController {

	@Autowired
	private PrivateEventService pEventSvc;

		private String username = "11";

//	@GetMapping("privateEvents/{eid}")
//	public PrivateEvent show(@PathVariable int eid, HttpServletRequest req, HttpServletResponse res,
//			Principal principal) {
////			PrivateEvent pEvent = pEventSvc.show(username, eid);
//		PrivateEvent pEvent = pEventSvc.show(principal.getName(), eid);
//		if (pEvent == null) {
//			res.setStatus(404);
//		}
//		return pEvent;
//	}
	@GetMapping("privateEvents/{eid}")
	public PrivateEvent show(@PathVariable int eid, HttpServletRequest req, HttpServletResponse res) {
			PrivateEvent pEvent = pEventSvc.show(username, eid);
//		PrivateEvent pEvent = pEventSvc.show(principal.getName(), eid);
		if (pEvent == null) {
			res.setStatus(404);
		}
		return pEvent;
	}
}
