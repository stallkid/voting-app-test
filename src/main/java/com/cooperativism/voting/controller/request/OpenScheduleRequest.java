package com.cooperativism.voting.controller.request;

import io.swagger.annotations.ApiModelProperty;
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
    private String endDate;
}
