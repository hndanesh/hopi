package com.hackathon.hopi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Passenger {

	private String name;
	private List<Long> rideList;
	private List<String> comments;
	private Boolean pendingReview;
	private Long pendingReiewId;

	@Id
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getRideList() {
		return rideList;
	}

	public void setRideList(List<Long> rideList) {
		this.rideList = rideList;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public Boolean getPendingReview() {
		return pendingReview;
	}

	public void setPendingReview(Boolean pendingReview) {
		this.pendingReview = pendingReview;
	}

	public Long getPendingReiewId() {
		return pendingReiewId;
	}

	public void setPendingReiewId(Long pendingReiewId) {
		this.pendingReiewId = pendingReiewId;
	}

}
