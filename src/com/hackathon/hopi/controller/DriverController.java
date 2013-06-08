package com.hackathon.hopi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hackathon.hopi.model.Driver;
import com.hackathon.hopi.service.GenericManager;

@Controller
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	GenericManager<Driver, String> driverManager;
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public String getDrivers(HttpServletRequest request, ModelMap model){
			List<Driver> results=null;
		if(null!=request.getParameter("name")){
			results=new ArrayList<Driver>();
			results.add(driverManager.get(request.getParameter("name")));
		}
		else
			results=driverManager.getAll();
		
		if (results.isEmpty()) {
			model.addAttribute("rideList", null);
		} else {
			model.addAttribute("rideList", results);
		}
		
		return "driver/get";
	}

}
