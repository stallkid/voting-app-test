package com.cooperativism.voting.controller.request;

import com.cooperativism.voting.domain.enums.VoteEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VoteRequest {
    private String scheduleId;
    private String cpf;
    private VoteEnum vote;
}
