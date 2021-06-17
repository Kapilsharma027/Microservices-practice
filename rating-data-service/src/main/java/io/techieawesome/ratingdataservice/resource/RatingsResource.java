package io.techieawesome.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.techieawesome.ratingdataservice.models.Rating;
import io.techieawesome.ratingdataservice.models.UserRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRatings getUserRatings(@PathVariable("userId") String userId) {
    	List<Rating> ratings = Arrays.asList(new Rating("krish", 14),new Rating("krish 2", 13));
    	 UserRatings userRatings= new UserRatings();
    	 userRatings.setUserratings(ratings);
    	
    	 return userRatings;

    }

}
