package com.hackathon.hopi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.hackathon.hopi.model.Driver;
import com.hackathon.hopi.model.Passenger;
import com.hackathon.hopi.model.Ride;
import com.hackathon.hopi.service.GenericManager;

@Controller
@RequestMapping("/ride")
public class RideController {

	@Autowired
	GenericManager<Ride, Long> rideManager;
	
	@Autowired
	GenericManager<Driver, String> driverManager;

	@Autowired
	GenericManager<Passenger, String> passengerManager;

	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody List<Ride> getRides(HttpServletRequest request, ModelMap model){
		List<Ride> results=rideManager.getAll();
		return results;
	}
	
	@RequestMapping(value = "/getpending", method = RequestMethod.GET)
	public @ResponseBody Ride getPendingReviews(HttpServletRequest request, ModelMap model){
		Ride ride=null;
		if(null!=request.getParameter("id")){
			Passenger passenger = null;
			try{
				passenger=passengerManager.get(request.getParameter("id"));
			}catch(EntityNotFoundException e){
				model.addAttribute("ride", ride);
			}
			if(null!=passenger&&passenger.getHasPendingReview())
				ride=rideManager.get(passenger.getPendingReiew());
		}
		return ride;
	}
	

/*	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(HttpServletRequest request, ModelMap model){
		UserService userService = UserServiceFactory.getUserService();
		return userService.createLoginURL(request.getRequestURI());
	}*/


	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddUserPage(ModelMap model) {
		return "ride/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addRide(HttpServletRequest request, ModelMap model)
			throws ParseException {
/*		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = sdf.parse(request.getParameter("time"));

		Ride ride = new Ride();
		
		
		ride.setFrom(request.getParameter("from"));
		ride.setFromLat(Float.parseFloat(request.getParameter("fromLat")));
		ride.setFromLng(Float.parseFloat(request.getParameter("fromLng")));
		ride.setTo(request.getParameter("to"));
		ride.setToLat(Float.parseFloat(request.getParameter("toLat")));
		ride.setToLat(Float.parseFloat(request.getParameter("toLng")));
		ride.setFare(Double.parseDouble(request.getParameter("fare")));
		ride.setTime(date);
		
		ride.setPhoneNo(request.getParameter("phoneNo"));
		ride.setPlateNo(request.getParameter("plateNo"));
		
/*		String userId= null!=user? user.getUserId():request
					.getParameter("userId");
*/		
		ride.setUserName(request.getParameter("userId"));

		//ride.setUserName(userId);

		if(!driverManager.exists(request.getParameter("userId"))){
			Driver driver=new Driver();
			driver.setName(request.getParameter("userId"));
			driver.setPhoneNo(request.getParameter("phoneNo"));
			driver.setPlateNo(request.getParameter("plateNo"));
			driver.setRating(0);
			
			ride.setDriverRating(0);
			driverManager.save(driver);
		}else{
			Driver driver=driverManager.get(request.getParameter("userId"));
			ride.setDriverRating(driver.getRating());
		}
		
		rideManager.save(ride);

		return new ModelAndView("redirect:get");

	}
	
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public ModelAndView registerRide(HttpServletRequest request, ModelMap model){
		if(null!=request.getParameter("id")&&
				null!=request.getParameter("passengerName")){
			
			Ride ride=null;
			if(rideManager.exists(Long.parseLong(request.getParameter("id")))){
				ride=rideManager.get(Long.parseLong(request.getParameter("id")));
				ride.getPassengers().add(request.getParameter("passengerName"));
				rideManager.save(ride);
				
				Passenger passenger=null;
				if(passengerManager.exists(request.getParameter("passengerName"))){
					passenger=passengerManager.get(request.getParameter("passengerName"));
				}else{
					passenger=new Passenger();
					passenger.setName(request.getParameter("passengerName"));
				}
				passenger.setHasPendingReview(true);
				passenger.setPendingReiew(Long.parseLong(request.getParameter("id")));
				passengerManager.save(passenger);

			}
		}
			
		return new ModelAndView("/home");
	}
	
	@RequestMapping(value="/rate", method= RequestMethod.POST)
	public ModelAndView rateRide(HttpServletRequest request, ModelMap model){
		Driver driver=driverManager.get(request.getParameter("driver"));
		int punctuality=Integer.parseInt(request.getParameter("punctuality"));
		int driving=Integer.parseInt(request.getParameter("driving"));
		int conduct=Integer.parseInt(request.getParameter("conduct"));
		
		driver.setRating(punctuality+driving+conduct);
		
		driverManager.save(driver);
		
		Passenger passenger=passengerManager.get(request.getParameter("passenger"));
		passenger.setHasPendingReview(false);
		
		passengerManager.save(passenger);
		
		return new ModelAndView("/home");
	}

}
