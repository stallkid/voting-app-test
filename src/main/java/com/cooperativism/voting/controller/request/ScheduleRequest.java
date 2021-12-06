package com.cooperativism.voting.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleRequest {
    private String id;
    private String name;
    private String description;
    private String endDate;
}
