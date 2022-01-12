package com.cooperativism.voting.controller.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OpenScheduleRequest {
    @ApiModelProperty(notes = "ID da pauta", required = true)
    private String scheduleId;
    @ApiModelProperty(notes = "Data de fechamento da Pauta")
    @ApiParam(value = "Data padrão como vazio, adiciona 1 minuto na hora apartir de agora", required = true, defaultValue = "", allowEmptyValue = true)
    private String endDate;
}
