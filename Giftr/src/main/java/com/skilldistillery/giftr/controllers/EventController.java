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

import com.skilldistillery.giftr.entities.Event;
import com.skilldistillery.giftr.services.EventService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class EventController {

	private String username = "11";

	@Autowired
	private EventService eventSvc;

	@GetMapping("events")
	public List<Event> index(HttpServletResponse res, Principal principal) {
		List<Event> eventsForUser = eventSvc.index(username);
		if (eventsForUser == null) {
			res.setStatus(404);
		}
		return eventsForUser;
	}

	@GetMapping("events/{eid}")
	public Event show(HttpServletResponse res, @PathVariable int eid, Principal principal) {
		Event event = eventSvc.show(principal.getName(), eid);
		if (event == null) {
			res.setStatus(404);
		}
		return event;
	}

	@PostMapping(path = "events")
	public Event create(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@RequestBody Event event) {
		event = eventSvc.create(principal.getName(), event);
		if (event == null) {
			res.setStatus(404);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(event.getUser());
			String urlstr = url.toString();
			res.setHeader("Location", urlstr);
		}
		return event;
	}

	@PutMapping(path = "events/{eid}")
	public Event update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int eid,
			@RequestBody Event event) {

		try {
			event = eventSvc.update(principal.getName(), eid, event);
			if (event == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}

		return event;
	}

	@DeleteMapping("events/{eid}")
	public void disable(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int eid) {
		boolean disabled = eventSvc.disable(principal.getName(), eid);
		if (disabled) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}
//
//	@GetMapping("events")
//	public List<Event> index(HttpServletResponse res) {
//		List<Event> eventsForUser = eventSvc.index(username);
//		if (eventsForUser == null) {
//			res.setStatus(404);
//		}
//		return eventsForUser;
//	}
//
//	@GetMapping("events/{eid}")
//	public Event show(HttpServletResponse res, @PathVariable int eid) {
//		Event event = eventSvc.show(username, eid);
//		if (event == null) {
//			res.setStatus(404);
//		}
//		return event;
//	}
//
//	@PostMapping("events")
//	public Event create(HttpServletRequest req, HttpServletResponse res, @RequestBody Event event) {
//		try {
//			event = eventSvc.create(username, event);
//			res.setStatus(201);
//			res.setHeader("Location", "api/events/" + event.getId());
//		} catch (Exception e) {
//			res.setStatus(400);
//		}
//		return eventSvc.create(username, event);
//	}
//
//	@PutMapping("events/{eid}")
//	public Event update(HttpServletRequest req, HttpServletResponse res, @PathVariable int eid,
//			@RequestBody Event event) {
//
//		try {
//			event = eventSvc.update(username, eid, event);
//			if (event == null) {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			res.setStatus(400);
//		}
//
//		return event;
//	}
//
//	@DeleteMapping("events/{eid}")
//	public void disable(HttpServletRequest req, HttpServletResponse res, @PathVariable int eid) {
//		boolean disabled = eventSvc.disable(username, eid);
//		if (disabled) {
//			res.setStatus(204);
//		} else {
//			res.setStatus(404);
//		}
//	}
//}
}
