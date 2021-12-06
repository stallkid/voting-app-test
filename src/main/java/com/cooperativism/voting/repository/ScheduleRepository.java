package com.cooperativism.voting.repository;

import com.cooperativism.voting.controller.response.VoteResponse;
import com.cooperativism.voting.domain.Schedule;
import com.mongodb.BasicDBObject;
import org.bson.json.JsonObject;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    Optional<Schedule> findScheduleByName(final String name);

//    @Aggregation(pipeline = {"[\n" +
//            "  { \"$facet\": {\n" +
//            "    \"totalVotes\": [\n" +
//            "      { \"$match\": {\"_id\": ObjectId(\"61abe2220d0cac311f682e87\")}},\n" +
//            "      { \"$unwind\": \"$votes\" },\n" +
//            "      { \"$unwind\": \"$votes.vote\" },\n" +
//            "      { \"$match\": { \"votes.vote\": {'$exists': true} } },\n" +
//            "      { \"$count\": \"Total\" }\n" +
//            "    ],\n" +
//            "    \"votesForYesCount\": [\n" +
//            "      { \"$match\": {\"_id\": ObjectId(\"61abe2220d0cac311f682e87\")}},\n" +
//            "      { \"$unwind\": \"$votes\" },\n" +
//            "      { \"$unwind\": \"$votes.vote\" },\n" +
//            "      { \"$match\": { \"votes.vote\": 'SIM' } },\n" +
//            "      { \"$count\": \"SIM\" },\n" +
//            "    ],\n" +
//            "    \"votesForNoCount\": [\n" +
//            "      { \"$match\": {\"_id\": ObjectId(\"61abe2220d0cac311f682e87\")}},\n" +
//            "      { \"$unwind\": \"$votes\" },\n" +
//            "      { \"$unwind\": \"$votes.vote\" },\n" +
//            "      { \"$match\": { \"votes.vote\": 'NAO' } },\n" +
//            "      { \"$count\": \"NAO\" }\n" +
//            "    ]\n" +
//            "  }},\n" +
//            "  { \"$project\": {\n" +
//            "    \"totalVotes\":{ \"$arrayElemAt\": [\"$totalVotes.Total\", 0] },\n" +
//            "    \"votesForYesCount\": { \"$arrayElemAt\": [\"$votesForYesCount.SIM\", 0] },\n" +
//            "    \"votesForNoCount\": { \"$arrayElemAt\": [\"$votesForNoCount.NAO\", 0] }\n" +
//            "  }}\n" +
//            "]"})
    @Aggregation(pipeline = "{ \"$match\": {\"_id\": ObjectId(\"61abe2220d0cac311f682e87\")}},\n" +
            "      { \"$unwind\": \"$votes\" },\n" +
            "      { \"$unwind\": \"$votes.vote\" },\n" +
            "      { \"$match\": { \"votes.vote\": {'$exists': true} } },\n" +
            "      { \"$count\": \"Total\" }")
    AggregationResults<Schedule> countAllVotes(final String id);

}
