package com.cooperativism.voting.controller.request;

import com.cooperativism.voting.domain.enums.VoteEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VoteRequest {
    @ApiModelProperty(notes = "ID da Pauta", required = true)
    private String scheduleId;
    @ApiModelProperty(notes = "CPF do colaborador", required = true)
    private String cpf;
    @ApiModelProperty(notes = "Voto do colaborador", required = true, allowableValues = "SIM/NAO")
    private VoteEnum vote;
}
