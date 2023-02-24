package vttp2022.csf.assessment.server.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.repositories.MapCache;
import vttp2022.csf.assessment.server.repositories.RestaurantRepository;

public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Autowired
	private MapCache mapCache;

	// TODO Task 2 - DONE
	// Use the following method to get a list of cuisines 
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public Optional<List<String>> getCuisines() {
		// Implmementation in here
		Optional<List<String>> opt = restaurantRepo.getCuisines();
		if (opt.isEmpty())
			return Optional.empty();

		List<String> listOfCuisines = new LinkedList<>();
		listOfCuisines = opt.get();
		List<String> listOfCuisines2 = new LinkedList<>();
		for (String cuisine : listOfCuisines) {
			listOfCuisines2.add(cuisine.replace("/","_"));
		}

		return Optional.of(listOfCuisines2);
	}

	// TODO Task 3 - DONE
	// Use the following method to get a list of restaurants by cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	public List<String> getRestaurantsByCuisine(String cuisine) {
		// Implmementation in here
			Optional<List<Restaurant>> opt = restaurantRepo.getRestaurantsByCuisine(cuisine);
			

			List<Restaurant> resListByCui = new LinkedList<>();
			resListByCui = opt.get();
			List<String> resListByCui2 = new LinkedList<>();
			for (Restaurant res : resListByCui) {
				resListByCui2.add(res.getName().replace("_","//"));
			}

		return resListByCui2;
	}

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	public Optional<Restaurant> getRestaurant(???) {
		// Implmementation in here
		
	}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	public void addComment(Comment comment) {
		// Implmementation in here
		
	}
	//
	// You may add other methods to this class
}
