package com.cooperativism.voting.mapper;

import com.cooperativism.voting.controller.request.VoteRequest;
import com.cooperativism.voting.controller.response.VoteResponse;
import com.cooperativism.voting.domain.Schedule;
import com.cooperativism.voting.domain.Votes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotesMapper {

    Votes toVotes(final VoteRequest voteRequest);

    @Mapping(target = "totalVotesForYesCount", source = "schedule.voteResults.yesVotes")
    @Mapping(target = "totalVotesForNoCount", source = "schedule.voteResults.noVotes")
    VoteResponse toVoteResponse(final Schedule schedule);

}
