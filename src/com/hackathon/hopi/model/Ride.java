package com.hackathon.hopi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hackathon.hopi.json.JsonDateSerializer;

@Entity
@JsonAutoDetect
public class Ride {
	
	private Long id;
	private String from;
	private Float fromLat;
	private Float fromLng;
	private String to;
	private Float toLat;
	private Float toLng;
	private String plateNo;
	private String userName;
	private String phoneNo;
	private Double fare;
	private Date time;
	private List<String> passengers;
	private int driverRating;

	public Ride(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Float getFromLat() {
		return fromLat;
	}

	public void setFromLat(Float fromLat) {
		this.fromLat = fromLat;
	}

	public Float getFromLng() {
		return fromLng;
	}

	public void setFromLng(Float fromLng) {
		this.fromLng = fromLng;
	}

	public Float getToLat() {
		return toLat;
	}

	public void setToLat(Float toLat) {
		this.toLat = toLat;
	}

	public Float getToLng() {
		return toLng;
	}

	public void setToLng(Float toLng) {
		this.toLng = toLng;
	}
	
	public List<String> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<String> passengers) {
		this.passengers = passengers;
	}
	
	public int getDriverRating() {
		return driverRating;
	}

	public void setDriverRating(int driverRating) {
		this.driverRating=driverRating;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", from=" + from + ", fromLat=" + fromLat
				+ ", fromLng=" + fromLng + ", to=" + to + ", toLat=" + toLat
				+ ", toLng=" + toLng + ", plateNo=" + plateNo + ", userName="
				+ userName + ", phoneNo=" + phoneNo + ", fare=" + fare
				+ ", time=" + time + "]";
	}	
	

}
