package io.techieawesome.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.techieawesome.moviecatalogservice.models.CatalogItem;



@RestController
@RequestMapping("/catalog")
public class MovieCatelogResource {
	  @RequestMapping("/{userId}")
	    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		  CatalogItem CI = new CatalogItem("Transformer", "An action movie", 10);
	       return Arrays.asList(CI);
	    }
}
