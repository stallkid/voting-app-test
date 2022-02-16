package com.cooperativism.voting.service;

import com.cooperativism.voting.client.CpfValidatorClient;
import com.cooperativism.voting.client.CpfValidatorEnum;
import com.cooperativism.voting.client.response.CpfValidatorResponse;
import com.cooperativism.voting.domain.Schedule;
import com.cooperativism.voting.domain.Votes;
import com.cooperativism.voting.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private ScheduleServiceImpl scheduleService;
    @Autowired
    private CpfValidatorClient cpfValidator;

    public void voteForSchedule(final String scheduleId, final Votes vote) {
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        int endDateComparison = schedule.getEndDate().compareTo(LocalDateTime.now());
        CpfValidatorResponse cpfIsValid = cpfValidator.validateCpf(vote.getCpf());
        if (cpfIsValid.getStatus().equals(CpfValidatorEnum.UNABLE_TO_VOTE)) throw new DataIntegrityException("Unable to vote");
        if (endDateComparison < 0) throw new DataIntegrityException("Schedule session is closed");
        boolean alreadyVoted =  schedule.getVotes() != null ? schedule.getVotes().stream()
            .anyMatch(votes -> vote.getCpf().equals(votes.getCpf())) : false;
        if (alreadyVoted) throw new DataIntegrityException("Schedule already has this vote");
        scheduleService.registerAssociateVote(vote, scheduleId);
    }

}
