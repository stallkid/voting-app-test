package com.cooperativism.voting.controller;

import com.cooperativism.voting.controller.request.OpenScheduleRequest;
import com.cooperativism.voting.controller.request.ScheduleRequest;
import com.cooperativism.voting.controller.request.VoteRequest;
import com.cooperativism.voting.controller.response.VoteResponse;
import com.cooperativism.voting.domain.Schedule;
import com.cooperativism.voting.mapper.ScheduleMapper;
import com.cooperativism.voting.mapper.VotesMapper;
import com.cooperativism.voting.service.ScheduleServiceImpl;
import com.cooperativism.voting.service.VoteServiceImpl;
import io.swagger.annotations.ApiOperation;
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
    private ScheduleServiceImpl scheduleService;
    @Autowired
    private VoteServiceImpl voteService;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private VotesMapper votesMapper;

    @ApiOperation(value = "Cria uma pauta", response = Schedule.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schedule> createSchedule(@Valid @RequestBody final ScheduleRequest request) {
        log.info("Creating schedule with Request: {}",request);
        Schedule schedule = scheduleService.createSchedule(scheduleMapper.toSchedule(request));
        return ResponseEntity.ok().body(schedule);
    }

    @ApiOperation(value = "Abre uma pauta com a data de finalização das votações")
    @PostMapping(value = "/open", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> openScheduleSession(
            @Valid @RequestBody final OpenScheduleRequest request
    ) {
        log.info("Opening Schedule to vote: {}", request);
        scheduleService.openSchedule(scheduleMapper.toSchedule(request), request.getScheduleId());
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Busca todas as pautas", response = Schedule.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        log.info("Starting to fetch all Schedules");
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok().body(schedules);
    }

    @ApiOperation(value = "Busca a pauta pelo ID", response = Schedule.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schedule> getScheduleByName(@PathVariable final String id) {
        log.info("Starting to fetch schedule by id: {}", id);
        Schedule schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok().body(schedule);
    }

    @ApiOperation(value = "Atualiza a pauta usando o ID", response = Schedule.class)
    @PutMapping(value = "/{id}")
    public ResponseEntity<Schedule> updateSchedule(
            @PathVariable final String id,
            @RequestBody final ScheduleRequest scheduleRequest
    ) {
        log.info("Starting to update Schedule with scheduleId: {}", id);
        Schedule schedule = scheduleService.updateSchedule(scheduleMapper.toSchedule(scheduleRequest), id);
        return ResponseEntity.ok().body(schedule);
    }

    @ApiOperation(value = "Deleta a pauta pelo ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSchedule( @PathVariable final String id) {
        log.info("Starting to delete the Schedule with Id: {}", id);
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Registra o voto na pauta")
    @PostMapping(value = "/vote",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> voteOnScheduleSession(
            @Valid @RequestBody final VoteRequest request
    ) {
        log.info("Associate started to voting: {}",request);
        voteService.voteForSchedule(request.getScheduleId(), votesMapper.toVotes(request));
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Busca a contagem dos votos da pauta pelo Id")
    @GetMapping(value = "/vote/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteResponse> votesCount(@PathVariable String id) {
        VoteResponse voteResponse = scheduleService.countingVotes(id);
        return ResponseEntity.ok().body(voteResponse);
    }
}
