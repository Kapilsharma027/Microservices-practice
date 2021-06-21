package io.techieawesome.moviecatalogservice.models;

import java.util.List;

public class UserRatings {
	private String userId;
	

	private List<Rating> userratings;
	
	public UserRatings() {
		
	}
	
	public UserRatings(String userId, List<Rating> userratings) {
	
		this.userId = userId;
		this.userratings = userratings;
	}

	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Rating> getUserratings() {
		return userratings;
	}

	public void setUserratings(List<Rating> userratings) {
		this.userratings = userratings;
	}

	
}
