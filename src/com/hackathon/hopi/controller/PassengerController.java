package com.hackathon.hopi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.hopi.model.Passenger;
import com.hackathon.hopi.service.GenericManager;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
	
	@Autowired
	GenericManager<Passenger,String> passengerManager;
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public @ResponseBody List<Passenger> getPassengers(HttpServletRequest request, ModelMap model){
		List<Passenger> results=null;
		if(null!=request.getParameter("name")){
			results=new ArrayList<Passenger>();
			results.add(passengerManager.get(request.getParameter("name")));
		}else
			results=passengerManager.getAll();
		
		return results;
	}
}
