package com.cooperativism.voting.service;

import com.cooperativism.voting.domain.Votes;

public interface VoteService {

    void voteForSchedule(final String scheduleId, final Votes vote);
}
