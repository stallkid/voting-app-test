package com.cooperativism.voting.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteResults {
    @ApiModelProperty(notes = "Contagem de votos SIM na Pauta")
    private Long yesVotes;
    @ApiModelProperty(notes = "Contagem de votos NAO na Pauta")
    private Long noVotes;
}
