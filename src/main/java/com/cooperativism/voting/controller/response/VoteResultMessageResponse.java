package com.cooperativism.voting.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VoteResultMessageResponse {
    private String id;
    private String name;
    private VoteResponse voteResult;
}
