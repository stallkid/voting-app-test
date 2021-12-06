package com.cooperativism.voting.service;

import com.cooperativism.voting.controller.response.VoteResponse;
import com.cooperativism.voting.domain.Schedule;
import com.cooperativism.voting.domain.VoteResults;
import com.cooperativism.voting.domain.Votes;
import com.cooperativism.voting.domain.enums.VoteEnum;
import com.cooperativism.voting.exception.DataIntegrityException;
import com.cooperativism.voting.exception.ScheduleNotFoundException;
import com.cooperativism.voting.mapper.VotesMapper;
import com.cooperativism.voting.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private VotesMapper votesMapper;

    public Schedule createSchedule(final Schedule schedule) {
        log.info("Saving Schedule");
        return repository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        log.info("Fetching all Schedules");
        return repository.findAll();
    }

    public Schedule getScheduleByName(final String name) {
        log.info("Fetching Schedule by name");
        return repository.findScheduleByName(name)
            .orElseThrow(() -> {
                log.error("Error while trying to fetch the Schedule with name: " + name);
                return new ScheduleNotFoundException(("Schedule Not Found With Name: " + name));
            });
    }

    public Schedule getScheduleById(final String id) {
        log.info("Fetching Schedule by id");
        return repository.findById(id)
            .orElseThrow(() -> {
                log.error("Error while trying to fetch the Schedule with Id: " + id);
                return new ScheduleNotFoundException("Schedule Not Found with Id: " + id);
            });
    }

    public Schedule updateSchedule(final Schedule newSchedule, final String id) {
        Schedule schedule = getScheduleById(id);
        log.info("Updating Schedule: {}", schedule);
        schedule.setName(newSchedule.getName());
        schedule.setDescription(newSchedule.getDescription());
        if (newSchedule.getEndDate() != null) {
            schedule.setEndDate(newSchedule.getEndDate());
        }
        return repository.save(schedule);
    }

    public void deleteSchedule(final String id) {
        Schedule schedule = getScheduleById(id);
        log.info("Deleting Schedule: {}", schedule);
        try {
            repository.delete(schedule);
        } catch (DataIntegrityException exc) {
            log.error("Error while deleting the Schedule with Id: " + id);
            throw new DataIntegrityException("Unable to delete this Schedule");
        }
    }

    public void openSchedule(final Schedule newSchedule, final String scheduleId) {
        Schedule schedule = getScheduleById(scheduleId);
        log.info("Opening Schedule: {}", schedule);
        if (newSchedule.getEndDate() == null) {
            schedule.setEndDate(LocalDateTime.now().plusMinutes(1));
        } else {
            schedule.setEndDate(newSchedule.getEndDate());
        }
        repository.save(schedule);
    }

    public void registerAssociateVote(final Votes vote, final String scheduleId) {
        log.info("Voting for Schedule with Id: {}", scheduleId);
        Schedule schedule = getScheduleById(scheduleId);
        if (schedule.getVotes() == null) {
            schedule.setVotes(Collections.singletonList(vote));
        } else {
            schedule.getVotes().add(vote);
        }
        schedule.setVoteResults(sumScheduleVote(schedule.getVoteResults(), vote));
        repository.save(schedule);
    }

    public VoteResults sumScheduleVote(VoteResults voteResults, Votes vote) {
        log.info("Adding vote count to the database");
        if (voteResults == null) {
            if (vote.getVote().equals(VoteEnum.SIM)) {
                return new VoteResults(1L, 0L);
            }
            return new VoteResults(0L, 1L);
        }
        if (vote.getVote().equals(VoteEnum.SIM)) {
            voteResults.setYesVotes(voteResults.getYesVotes() + 1);
        } else {
            voteResults.setNoVotes(voteResults.getNoVotes() + 1);
        }
        return voteResults;
    }

    public VoteResponse countingVotes(final String scheduleId) {
        log.info("Fetching votes for schedule with id: {}", scheduleId);
        VoteResponse result = votesMapper.toVoteResponse(getScheduleById(scheduleId));
        result.setTotalVotesCount(result.getTotalVotesForYesCount() + result.getTotalVotesForNoCount());
        log.info("Votes result: {}", result);
        return result;
    }

}
