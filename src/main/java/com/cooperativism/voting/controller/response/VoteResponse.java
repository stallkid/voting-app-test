package com.cooperativism.voting.controller.response;

import lombok.Data;

@Data
public class VoteResponse {
    private Integer totalVotesCount;
    private Integer totalVotesForYesCount;
    private Integer totalVotesForNoCount;
}
