package io.techieawesome.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.techieawesome.moviecatalogservice.models.Rating;
import io.techieawesome.moviecatalogservice.models.UserRatings;

@Service
public class UserRatingInfo {

	@Autowired
	private RestTemplate restTemplete;
	
	 @HystrixCommand(fallbackMethod = "getFallBackUserRating")
	  public UserRatings getUserRating(@PathVariable("userId") String userId){
		return restTemplete.getForObject("http://movie-rating-service/ratingsdata/users/"+userId, UserRatings.class);
	  }
	  
	  public UserRatings getFallBackUserRating(@PathVariable("userId") String userId){
		  UserRatings userRating = new UserRatings();
		  userRating.setUserId(userId);
		  userRating.setUserratings(Arrays.asList(new Rating("0",0)));
		  return userRating;
	  }
	
}
