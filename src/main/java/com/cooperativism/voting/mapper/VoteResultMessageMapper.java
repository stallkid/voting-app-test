package com.cooperativism.voting.mapper;

import com.cooperativism.voting.controller.response.VoteResultMessageResponse;
import com.cooperativism.voting.domain.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteResultMessageMapper {

    @Mapping(target = "voteResult.totalVotesCount", expression = "java(voteResults.getYesVotes() + voteResults.getNoVotes())")
    @Mapping(target = "voteResult.totalVotesForYesCount", source = "schedule.voteResults.yesVotes")
    @Mapping(target = "voteResult.totalVotesForNoCount", source = "schedule.voteResults.noVotes")
    VoteResultMessageResponse toVoteResultMessageResponse(final Schedule schedule);
}
