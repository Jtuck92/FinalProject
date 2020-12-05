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

import com.skilldistillery.giftr.entities.PrivateEvent;
import com.skilldistillery.giftr.services.PrivateEventService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class PrivateEventController {

	@Autowired
	private PrivateEventService pEventSvc;

	private String username = "11";

//// THIS IS THE PRINCIPAL Index METHOD 
//		@GetMapping("privateEvents")
//		public Set<PrivateEvent> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
//			Set<PrivateEvent> pEvent = pEventSvc.index(principal.getName());
//			if (pEvent == null) {
//				res.setStatus(404);
//			}
//			return pEvent;
//		}
//
//// THIS IS THE PRINCIPAL Show METHOD
//	@GetMapping("privateEvents/{eid}")
//	public PrivateEvent show(@PathVariable int eid, HttpServletRequest req, HttpServletResponse res,
//			Principal principal) {
//		PrivateEvent pEvent = pEventSvc.show(principal.getName(), eid);
//		if (pEvent == null) {
//			res.setStatus(404);
//		}
//		return pEvent;
//	}
//
//// THIS IS THE PRINCIPAL Create METHOD
//	@PostMapping("privateEvents")
//	public PrivateEvent create(@RequestBody PrivateEvent pEvent, HttpServletRequest req, HttpServletResponse res, Principal principal) {
//
//		pEvent = pEventSvc.create(principal.getName(), pEvent);
//
//		if (pEvent == null) {
//			res.setStatus(404);
//		} else {
//			res.setStatus(201);
//			StringBuffer url = req.getRequestURL();
//			url.append("/").append(pEvent.getId());
//			req.setAttribute("Location", url.toString());
//		}
//		return pEventSvc.create(principal.getName(), pEvent);
//	}
//
////THIS IS THE PRINCIPAL Update METHOD
//	@PutMapping("privateEvents/{tid}")
//	public PrivateEvent update(@PathVariable int eid, @RequestBody PrivateEvent pEvent, HttpServletRequest req, HttpServletResponse res,
//			Principal principal) {
//		try {
//			pEvent = pEventSvc.update(principal.getName(), eid, pEvent);
//			if (pEvent == null) {
//				res.setStatus(404);
//				pEvent = null;
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//			pEvent = null;
//		}
//		return pEvent;
//	}
//
////THIS IS THE PRINCIPAL Delete METHOD
//	@DeleteMapping("privateEvents/{eid}")
//	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int eid, Principal principal) {
//		try {
//			if (pEventSvc.destroy(principal.getName(), eid)) {
//				res.setStatus(204);
//			} else {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//		}
//	}

///// THIS IS METHOD WITHOUT SECURITY
	@GetMapping("privateEvents")
	public List<PrivateEvent> index(HttpServletRequest req, HttpServletResponse res) {
		List<PrivateEvent> pEvent = pEventSvc.index(username);
		if (pEvent == null) {
			res.setStatus(404);
		}
		return pEvent;
	}

//// THIS IS METHOD WITHOUT SECURITY
	@GetMapping("privateEvents/{eid}")
	public PrivateEvent show(@PathVariable int eid, HttpServletRequest req, HttpServletResponse res) {
		PrivateEvent pEvent = pEventSvc.show(username, eid);
		if (pEvent == null) {
			res.setStatus(404);
		}
		return pEvent;
	}

////THIS IS METHOD WITHOUT SECURITY
	@PostMapping("privateEvents")
	public PrivateEvent create(@RequestBody PrivateEvent pEvent, HttpServletRequest req, HttpServletResponse res) {
		pEvent = pEventSvc.create(username, pEvent);
		if (pEvent == null) {
			res.setStatus(404);
		} else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(pEvent.getId());
			req.setAttribute("Location", url.toString());
		}
		return pEventSvc.create(username, pEvent);
	}
	
	
////THIS IS METHOD WITHOUT SECURITY
	@PutMapping("privateEvents/{eid}")
	public PrivateEvent update(@PathVariable int eid, @RequestBody PrivateEvent pEvent, HttpServletRequest req, HttpServletResponse res) {
		try {
			pEvent = pEventSvc.update(username, eid, pEvent);			
			if (pEvent == null) {
				res.setStatus(404);
				pEvent = null;
			}
		} catch (Exception e) {
			res.setStatus(400);
			pEvent = null;
		}
		return pEvent;
	}
	
	
////THIS IS METHOD WITHOUT SECURITY
	@DeleteMapping("privateEvents/{eid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int eid, Principal principal) {
		try {
			if (pEventSvc.destroy(username, eid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}
}
