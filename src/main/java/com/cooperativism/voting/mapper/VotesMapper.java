package com.cooperativism.voting.mapper;

import com.cooperativism.voting.controller.request.VoteRequest;
import com.cooperativism.voting.controller.response.VoteResponse;
import com.cooperativism.voting.domain.Votes;
import com.mongodb.BasicDBObject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VotesMapper {

    Votes toVotes(final VoteRequest voteRequest);

    VoteResponse toVoteResponse(final BasicDBObject dbObject);

}
