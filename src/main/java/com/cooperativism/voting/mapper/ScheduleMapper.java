package com.cooperativism.voting.mapper;

import com.cooperativism.voting.controller.request.OpenScheduleRequest;
import com.cooperativism.voting.controller.request.ScheduleRequest;
import com.cooperativism.voting.domain.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "votes", ignore = true)
    Schedule toSchedule(final ScheduleRequest scheduleRequest);

    @Mapping(target = "endDate", source = "scheduleRequest.endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Schedule toSchedule(final OpenScheduleRequest scheduleRequest);
}
