package com.cooperativism.voting.controller;

import com.cooperativism.voting.controller.request.OpenScheduleRequest;
import com.cooperativism.voting.controller.request.ScheduleRequest;
import com.cooperativism.voting.controller.response.VoteResponse;
import com.cooperativism.voting.domain.Schedule;
import com.cooperativism.voting.mapper.ScheduleMapper;
import com.cooperativism.voting.service.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;
    @Autowired
    private ScheduleMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schedule> createSchedule(@Valid @RequestBody final ScheduleRequest request) {
        log.info("Creating schedule with Request: {}",request);
        Schedule schedule = service.createSchedule(mapper.toSchedule(request));
        return ResponseEntity.ok().body(schedule);
    }

    @PostMapping(value = "/open", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> openScheduleSession(
            @Valid @RequestBody final OpenScheduleRequest request
    ) {
        log.info("Opening Schedule to vote: {}", request);
        service.openSchedule(mapper.toSchedule(request), request.getScheduleId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        log.info("Starting to fetch all Schedules");
        List<Schedule> schedules = service.getAllSchedules();
        return ResponseEntity.ok().body(schedules);
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schedule> getScheduleByName(@PathVariable final String name) {
        log.info("Starting to fetch schedule by name: {}", name);
        Schedule schedule = service.getScheduleByName(name);
        return ResponseEntity.ok().body(schedule);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Schedule> updateSchedule(
            @PathVariable final String id,
            @RequestBody final ScheduleRequest scheduleRequest
    ) {
        log.info("Starting to update Schedule with Id: {}", id);
        Schedule schedule = service.updateSchedule(mapper.toSchedule(scheduleRequest), id);
        return ResponseEntity.ok().body(schedule);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSchedule( @PathVariable final String id) {
        log.info("Starting to delete the Schedule with Id: {}", id);
        service.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/votes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteResponse> votesCount(@PathVariable String id) {
        VoteResponse voteResponse = service.countingVotes(id);
        return ResponseEntity.ok().body(voteResponse);
    }
}
