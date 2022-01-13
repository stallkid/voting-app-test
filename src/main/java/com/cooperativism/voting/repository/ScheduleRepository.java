package com.cooperativism.voting.repository;

import com.cooperativism.voting.domain.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    Optional<Schedule> findScheduleByName(final String name);

    @Query("{'$and': [{'voteResults': {'$exists': true} },{'status': false}, {'endDate': {'$lte': new Date()}}]}")
    List<Schedule> findAllClosedScheduleToSendToQueue();

}
