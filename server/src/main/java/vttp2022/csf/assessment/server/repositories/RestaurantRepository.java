package vttp2022.csf.assessment.server.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.StringOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.LatLng;
import vttp2022.csf.assessment.server.models.Restaurant;

public class RestaurantRepository {
	
	@Autowired
	private MongoTemplate template;

	// For Task 4
	public static Restaurant createNewRes (Document doc) {
		
		Restaurant r = new Restaurant();
		r.setRestaurantId(doc.getString("restaurant_id"));
		r.setName(doc.getString("name"));
		r.setCuisine(doc.getString("cuisine"));
		r.setAddress(doc.getString("address"));
		r.setMapURL(null);

		LatLng c = new LatLng();
		String[] coord = doc.get("coordinates").toString().split(",");
		c.setLatitude(Integer.parseInt(coord[1]));
		c.setLongitude(Integer.parseInt(coord[0]));
		r.setCoordinates(c);
		
		return r;
	}
	
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
			List<String> cuisines = template.findDistinct( new Query(), "cuisine", "restaurants", String.class);

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
        // resByCui = template.find(query, Document.class, "restaurants")
        //     .stream()
        //     .map(v -> {
        //         return create(v);
        //     })
        //     .toList();

			resByCui = template.find(query, Restaurant.class, "restaurants");

		return Optional.of(resByCui);
	}

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  
	/* 
		db.restaurants.aggregate([
			{
				$match: { name : "<name>" }
			},
			{
				$project: {
					_id: 0,
					name: 1,
					restaurant_id: 1,
					cuisine: 1,
					address: {
						$concat: [ "$address.building", ", ", "$address.street", ", ", "$address.zipcode", ", ", "$borough" ]
					},
					coordinates: "$address.coord"
				}
			}
		]);
	 */
	public Optional<Restaurant> getRestaurant(String name) {
		// Implmementation in here

		MatchOperation matchRated = Aggregation.match(
     		Criteria.where("name").is(name));
	
		ProjectionOperation projectRestaurant = Aggregation.project("name", "restaurant_id", "cuisine", "address", "coordinates")
     		.and("address.coord").as("coordinates")
     		.and(StringOperators.Concat.valueOf("address.building").concat(", ").concatValueOf("address.street").concat(", ").concatValueOf("address.zipcode").concat(", ").concatValueOf("borough")).as("address");

  		Aggregation pipeline = Aggregation.newAggregation(projectRestaurant);
 		AggregationResults<Document> results = template.aggregate(
      	pipeline, "restaurants", Document.class);

		if (!results.iterator().hasNext())
		  return Optional.empty();
	   
		// Get one result only
		Document doc = results.iterator().next();

		Restaurant task4Res = createNewRes(doc);

		String url = UriComponentsBuilder
    	.fromUriString(url)
    	.queryParam("name", "fred")
    	.queryParam("email", "fred@gmail.com")
    	.toUriString(); // Build a URL with query parameter. Query parameters will be URL safe

		RequestEntity req = RequestEntity
			.get("http://map.chuklee.com")
			.get
			.build();
		// Make the call to url
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = template.exchange(req, String.class);



		
		// so going to hardcode Restaurant Json Object to continue
		// String fakeName = name;
		// JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		// arrBuilder.add("-73.9985052")
		// 	.add("40.7141563")
		// 	.build();
		// Json.createObjectBuilder()
        //     .add("restaurant_id", "40827287")
        //     .add("name", "Ajisen Ramen")
        //     .add("cuisine", "Asian")
        //     .add("address", "14, Mott Street, 10013, Manhattan")
		// 	.add("coordinates", 
		// 		arrBuilder.add("-73.9985052")
		// 		.add("40.7141563")
		// 		.build())
        //     .build();

			RequestEntity<String> req = RequestEntity
					.get("http://map.chuklee.com")
					// .contentType(MediaType.APPLICATION_JSON)
					.headers("Accept", MediaType.IMAGE_PNG);
					// .body(json.toString(), String.class);

		
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
