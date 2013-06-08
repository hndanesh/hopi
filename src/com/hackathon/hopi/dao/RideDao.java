package com.hackathon.hopi.dao;

import java.util.Date;
import java.util.List;

import com.hackathon.hopi.model.Ride;

public interface RideDao extends GenericDao<Ride, Long>{
	public List<Ride> search(Float fromLat, Float fromLng,Float toLat,Float toLng,int radius);
	
	public List<Ride> search(String userName);
	
	public List<Ride> search(Date startTime);
}
