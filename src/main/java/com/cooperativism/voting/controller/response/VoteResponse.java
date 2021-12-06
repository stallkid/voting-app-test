package com.cooperativism.voting.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class VoteResponse {
    private Integer totalVotesCount;
    private Integer totalVotesForYesCount;
    private Integer totalVotesForNoCount;
}
