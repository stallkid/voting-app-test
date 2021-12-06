package com.cooperativism.voting.repository;

import com.cooperativism.voting.domain.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    Optional<Schedule> findScheduleByName(final String name);

}
