package com.cooperativism.voting.mapper;

import com.cooperativism.voting.controller.request.VoteRequest;
import com.cooperativism.voting.domain.Votes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    Votes toVotes(final VoteRequest voteRequest);
}
