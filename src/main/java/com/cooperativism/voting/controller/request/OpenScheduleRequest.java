package com.cooperativism.voting.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OpenScheduleRequest {
    private String scheduleId;
    private String endDate;
}
