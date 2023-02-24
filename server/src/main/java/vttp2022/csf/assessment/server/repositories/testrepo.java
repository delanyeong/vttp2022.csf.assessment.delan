package vttp2022.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;

public class testrepo {
    

    ProjectionOperation projectMovieSummary = Aggregation.project("name", "restaurant_id", "cuisine", "address", "coordinates")
     .and("plot").as("summary")
     .and(StringOperators.Concat.valueOf("name").concat(" (")
        .concatValueOf("rated").concat(")")).as(“title”);
  Aggregation pipeline = Aggregation.newAggregation(projectMovieSummary, sortByTitle);
  AggregationResults<Document> results = mongoTemplate.aggregate(
      pipeline, “movies”, Document.class);
}
