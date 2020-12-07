package com.skilldistillery.giftr.controllers;

import java.security.Principal;
import java.util.List;
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

import com.skilldistillery.giftr.entities.Address;
import com.skilldistillery.giftr.entities.PrivateComment;
import com.skilldistillery.giftr.entities.PrivateEvent;
import com.skilldistillery.giftr.services.AddressService;
import com.skilldistillery.giftr.services.PrivateEventService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RestController
@RequestMapping("api")
public class AddressController {

	@Autowired
	private AddressService addSvc;

	private String username = "11";


	// THIS IS THE PRINCIPAL Index METHOD
			@GetMapping("addresses")
			public Set<Address> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
				Set<Address> address = addSvc.index(principal.getName());
				if (address == null) {
					res.setStatus(404);
				}
				return address;
			}
	
	// THIS IS THE PRINCIPAL Show METHOD
		@GetMapping("addresses/{aid}")
		public Address show(@PathVariable int aid, HttpServletRequest req, HttpServletResponse res,
				Principal principal) {
			Address address = addSvc.show(principal.getName(), aid);
			if (address == null) {
				res.setStatus(404);
			}
			return address;
		}
	
	// THIS IS THE PRINCIPAL Create METHOD
		@PostMapping("addresses")
		public Address create(@RequestBody Address address, HttpServletRequest req, HttpServletResponse res, Principal principal) {
			address = addSvc.create(principal.getName(), address);
			if (address == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(address.getId());
				req.setAttribute("Location", url.toString());
			}
			return addSvc.create(principal.getName(), address);
		}
	
	// THIS IS THE PRINCIPAL Update METHOD
		@PutMapping("addresses/{aid}")
		public Address update(@PathVariable int aid, @RequestBody Address address, HttpServletRequest req, HttpServletResponse res,
				Principal principal) {
			try {
				address = addSvc.update(principal.getName(), aid, address);
				if (address == null) {
					res.setStatus(404);
					address = null;
				}
			} catch (Exception e) {
				res.setStatus(400);
				address = null;
			}
			return address;
		}
	
	// THIS IS THE PRINCIPAL Delete METHOD
		@DeleteMapping("addresses/{aid}")
		public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, Principal principal) {
			try {
				if (addSvc.destroy(principal.getName(), aid)) {
					res.setStatus(204);
				} else {
					res.setStatus(404);
				}
			} catch (Exception e) {
				res.setStatus(400);
			}
		}

//	///// THIS IS METHOD WITHOUT SECURITY
//	@GetMapping("addresses")
//	public Set<Address> index() {
//		return addSvc.index(username);
//	}
//
//	//// THIS IS METHOD WITHOUT SECURITY
//	@GetMapping("addresses/{aid}")
//	public Address show(@PathVariable int aid, HttpServletRequest req, HttpServletResponse res) {
//		Address address = addSvc.show(username, aid);
//		if (address == null) {
//			res.setStatus(404);
//		}
//		return address;
//	}
//
//	//// THIS IS METHOD WITHOUT SECURITY
//	@PostMapping("addresses")
//	public Address create(@RequestBody Address address, HttpServletRequest req, HttpServletResponse res) {
//		address = addSvc.create(username, address);
//	System.err.println(address);
//		if (address == null) {
//			res.setStatus(404);
//		} else {
//			res.setStatus(201);
//			StringBuffer url = req.getRequestURL();
//			url.append("/").append(address.getId());
//			req.setAttribute("Location", url.toString());
//		}
//		return addSvc.create(username, address);
//	}
//
//	//// THIS IS METHOD WITHOUT SECURITY
//	@PutMapping("addresses/{aid}")
//	public Address update(@PathVariable int aid, @RequestBody Address address, HttpServletRequest req,
//			HttpServletResponse res) {
//		try {
//			address = addSvc.update(username, aid, address);
//			if (address == null) {
//				res.setStatus(404);
//				address = null;
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//			address = null;
//		}
//		return address;
//	}
//
//	//// THIS IS METHOD WITHOUT SECURITY
//	@DeleteMapping("addresses/{aid}")
//	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, Principal principal) {
//		try {
//			if (addSvc.destroy(username, aid)) {
//				res.setStatus(204);
//			} else {
//				res.setStatus(404);
//			}
//		} catch (Exception e) {
//			res.setStatus(400);
//		}
//	}
}