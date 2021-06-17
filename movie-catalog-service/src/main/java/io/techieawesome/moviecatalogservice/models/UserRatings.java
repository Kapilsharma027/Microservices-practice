package io.techieawesome.moviecatalogservice.models;

import java.util.List;

public class UserRatings {
	private List<Rating> userratings;
	
	public UserRatings() {
		
	}
	
	public UserRatings(List<Rating> userratings) {
		
		this.userratings = userratings;
	}

	public List<Rating> getUserratings() {
		return userratings;
	}

	public void setUserratings(List<Rating> userratings) {
		this.userratings = userratings;
	}

	
}
