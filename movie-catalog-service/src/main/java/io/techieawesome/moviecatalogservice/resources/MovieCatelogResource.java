package io.techieawesome.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.techieawesome.moviecatalogservice.models.CatalogItem;
import io.techieawesome.moviecatalogservice.models.UserRatings;
import io.techieawesome.moviecatalogservice.services.MovieInfo;
import io.techieawesome.moviecatalogservice.services.UserRatingInfo;



@RestController
@RequestMapping("/catalog")
public class MovieCatelogResource {
	
	@Autowired
	private RestTemplate restTemplete;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
	
	  @RequestMapping("/{userId}")
	    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		  UserRatings ratings = userRatingInfo.getUserRating(userId);
				  
		  return ratings.getUserratings().stream().map(rating -> 
		  movieInfo.getCatalogItem(rating)).collect(Collectors.toList());
	       
	    }
}
