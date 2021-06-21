package io.techieawesome.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.techieawesome.moviecatalogservice.models.CatalogItem;
import io.techieawesome.moviecatalogservice.models.Movie;
import io.techieawesome.moviecatalogservice.models.Rating;
import io.techieawesome.moviecatalogservice.models.UserRatings;



@RestController
@RequestMapping("/catalog")
public class MovieCatelogResource {
	
	@Autowired
	private RestTemplate restTemplete;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	  @RequestMapping("/{userId}")
	  @HystrixCommand(fallbackMethod = "getFallBackCatalog")
	    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

//		  List<Rating> ratings = Arrays.asList(new Rating("13", 4),new Rating("14", 4));
//		 UserRatings ratings = restTemplete.getForObject("http://localhost:8082/ratingsdata/users/"+userId, UserRatings.class);
		  UserRatings ratings = restTemplete.getForObject("http://movie-rating-service/ratingsdata/users/"+userId, UserRatings.class);
		  return ratings.getUserratings().stream().map(rating -> {
			  Movie movie =  restTemplete.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			  
//			  Movie movie =  webClientBuilder.build()
//			  .get()
//			  .uri("http://localhost:8081/movies/"+rating.getMovieId())
//			  .retrieve()
//			  .bodyToMono(Movie.class)
//			  .block();
			
			  return new CatalogItem(movie.getName(), "descripting ", rating.getRating());
			  
		  }).collect(Collectors.toList());
	       
	    }
	  
	  public List<CatalogItem> getFallBackCatalog(@PathVariable("userId") String userId) {
		  return Arrays.asList(new CatalogItem("No Movie", "", 0));
	  }
}
