package com.cooperativism.voting.controller;

import com.cooperativism.voting.controller.request.VoteRequest;
import com.cooperativism.voting.controller.response.VoteResponse;
import com.cooperativism.voting.mapper.VotesMapper;
import com.cooperativism.voting.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/vote")
public class VoteController {

    @Autowired
    private VoteService service;
    @Autowired
    private VotesMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> voteOnScheduleSession(
            @RequestBody final VoteRequest request
    ) {
        log.info("Associate started to voting: {}",request);
        service.voteForSchedule(request.getScheduleId(), mapper.toVotes(request));
        return ResponseEntity.noContent().build();
    }
}
