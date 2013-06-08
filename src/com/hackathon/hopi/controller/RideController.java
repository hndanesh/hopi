package com.hackathon.hopi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.hackathon.hopi.model.Ride;
import com.hackathon.hopi.service.GenericManager;

@Controller
@RequestMapping("/ride")
public class RideController {

	@Autowired
	GenericManager<Ride, Long> rideManager;

	@Autowired
	public void setRideManager(
			@Qualifier("rideManager") GenericManager<Ride, Long> rideManager) {
		this.rideManager = rideManager;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getRides(HttpServletRequest request, ModelMap model)
			throws ParseException {
		List<Ride> results = rideManager.getAll();

		if (results.isEmpty()) {
			model.addAttribute("rideList", null);
		} else {
			model.addAttribute("rideList", results);
		}

		return "ride/get";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddUserPage(ModelMap model) {
		return "ride/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addRide(HttpServletRequest request, ModelMap model)
			throws ParseException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = sdf.parse(request.getParameter("time"));

		Ride ride = new Ride();
		ride.setFrom(request.getParameter("from"));
		ride.setFromLat(Float.parseFloat(request.getParameter("fromLat")));
		ride.setFromLng(Float.parseFloat(request.getParameter("fromLng")));
		ride.setTo(request.getParameter("to"));
		ride.setToLat(Float.parseFloat(request.getParameter("toLat")));
		ride.setToLat(Float.parseFloat(request.getParameter("toLng")));
		ride.setPlateNo(request.getParameter("plateNo"));
		ride.setUserName(user != null ? user.getUserId() : request
				.getParameter("userId"));
		ride.setPhoneNo(request.getParameter("phoneNo"));
		ride.setFare(Double.parseDouble(request.getParameter("fare")));
		ride.setTime(date);

		rideManager.save(ride);

		return new ModelAndView("redirect:get");

	}

}
