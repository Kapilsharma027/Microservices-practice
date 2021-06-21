package io.techieawesome.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.techieawesome.moviecatalogservice.models.CatalogItem;
import io.techieawesome.moviecatalogservice.models.Movie;
import io.techieawesome.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemplete;

	 @HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
	  public CatalogItem getCatalogItem(Rating rating){
		  Movie movie =  restTemplete.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		  return new CatalogItem(movie.getName(), "descripting ", rating.getRating());
		  
	  }
	  
	  public CatalogItem getFallBackCatalogItem(Rating rating) {
		  return new CatalogItem("No movie found", "", rating.getRating());
	  }
}
