package com.cooperativism.voting.controller.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class OpenScheduleRequest {
    @ApiModelProperty(notes = "ID da pauta", required = true)
    private String scheduleId;
    @ApiModelProperty(notes = "Data de fechamento da Pauta", allowableValues = "yyyy-mm-dd hh:mm:ss")
    @ApiParam(value = "Data padrão como vazio, adiciona 1 minuto na hora apartir de agora", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String endDate;
}
