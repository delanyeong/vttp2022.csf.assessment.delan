package vttp2022.csf.assessment.server.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;

public class RestaurantRepository {
	
	@Autowired
	private MongoTemplate template;

	// TODO Task 2 - DONE
	// Use this method to retrive a list of cuisines from the restaurant collection
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
		/* 
			db.restaurants.distinct('cuisine')
		*/
	public Optional<List<String>> getCuisines() {
		
		// Implmementation in here
			// List<String> cuisines = new LinkedList<>();
			// Query query = new Query();

			List<String> cuisines = template.findDistinct( new Query(), "cuisine", "restaurants", String.class);
				
			// template.find(query,Document.class,"restaurants")
			//     .stream()
			//     .map ( v -> { 
			//         return cuisines.add(Restaurant.create(v).getCuisine()); })
			//     .toList();

			return Optional.of(cuisines);

	}

	// TODO Task 3 - DONE
	// Use this method to retrive a all restaurants for a particular cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	//  
		/* 
			db.restaurants.find({'cuisine':'<cuisine>'})
		*/
	public Optional<List<Restaurant>> getRestaurantsByCuisine(String cuisine) {

		// Implmementation in here
		List<Restaurant> resByCui = new LinkedList<>();
		Criteria criteria = Criteria.where("cuisine").is(cuisine);
        Query query = Query.query(criteria);
        resByCui = template.find(query, Document.class, "restaurants")
            .stream()
            .map(v -> {
                return Restaurant.create(v);
            })
            .toList();

		return Optional.of(resByCui);
	}

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  
	public Optional<Restaurant> getRestaurant(???) {
		// Implmementation in here
		
	}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  
	public void addComment(Comment comment) {
		// Implmementation in here
		
	}
	
	// You may add other methods to this class

}
