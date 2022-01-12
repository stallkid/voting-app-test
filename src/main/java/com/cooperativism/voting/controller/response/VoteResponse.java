package com.cooperativism.voting.controller.response;

import lombok.Data;

@Data
public class VoteResponse {
    private Long totalVotesCount;
    private Long totalVotesForYesCount;
    private Long totalVotesForNoCount;
}
