package com.cooperativism.voting.controller.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleRequest {
    @ApiModelProperty(notes = "Nome da Pauta", required = true)
    private String name;
    @ApiModelProperty(notes = "Descrição da Pauta", required = true)
    private String description;
}
