package com.hackathon.hopi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Passenger {

	private String name;
	private List<Long> rideList;
	private List<String> comments;
	private Boolean hasPendingReview;
	private Long pendingReiew;

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

	public Boolean getHasPendingReview() {
		return hasPendingReview;
	}

	public void setHasPendingReview(Boolean hasPendingReview) {
		this.hasPendingReview = hasPendingReview;
	}

	public Long getPendingReiew() {
		return pendingReiew;
	}

	public void setPendingReiew(Long pendingReiew) {
		this.pendingReiew = pendingReiew;
	}

}
