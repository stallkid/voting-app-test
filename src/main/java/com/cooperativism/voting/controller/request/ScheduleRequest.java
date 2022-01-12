package com.cooperativism.voting.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleRequest {
    @ApiModelProperty(notes = "ID da Pauta", required = true)
    private String id;
    @ApiModelProperty(notes = "Nome da Pauta", required = true)
    private String name;
    @ApiModelProperty(notes = "Descrição da Pauta", required = true)
    private String description;
    @ApiModelProperty(notes = "Data de fechamento da Pauta", required = true)
    private String endDate;
}
