package com.cooperativism.voting.service;

import com.cooperativism.voting.controller.response.VoteResponse;
import com.cooperativism.voting.domain.Schedule;
import com.cooperativism.voting.domain.VoteResults;
import com.cooperativism.voting.domain.Votes;

import java.util.List;

public interface ScheduleService {

    Schedule createSchedule(final Schedule schedule);

    List<Schedule> getAllSchedules();

    Schedule getScheduleByName(final String name);

    Schedule getScheduleById(final String id);

    Schedule updateSchedule(final Schedule newSchedule, final String id);

    void deleteSchedule(final String id);

    void openSchedule(final Schedule newSchedule, final String scheduleId);

    void registerAssociateVote(final Votes vote, final String scheduleId);

    VoteResults sumScheduleVote(VoteResults voteResults, Votes vote);

    VoteResponse countingVotes(final String scheduleId);
}
