package com.cooperativism.voting.service;

import com.cooperativism.voting.domain.Schedule;
import com.cooperativism.voting.domain.Votes;
import com.cooperativism.voting.domain.enums.VoteEnum;
import com.cooperativism.voting.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    ScheduleRepository repository;

    @InjectMocks
    ScheduleService scheduleService;

    @Test
    void shouldCreateScheduleSuccessfully() {
        final Schedule schedule = new Schedule(null,"test", "testDesc", LocalDateTime.now(), null, null, null);

        Mockito.when(repository.save(schedule)).thenReturn(schedule);

        Schedule expected = scheduleService.createSchedule(schedule);

        assertNotNull(expected);
        Mockito.verify(repository).save(Mockito.any(Schedule.class));
    }

    @Test
    void getAllSchedules() {
        final Schedule scheduleOne = new Schedule("135adgadgade311", "test1", "test1 desc", LocalDateTime.now(), null, null, null);
        final Schedule scheduleTwo = new Schedule("135adgadgade312", "test2", "test2 desc", LocalDateTime.now(), null, null, null);
        final Schedule scheduleThree = new Schedule("135adgadgade313", "test3", "test3 desc", LocalDateTime.now(), null, null, null);
        List<Schedule> schedules = List.of(scheduleOne, scheduleTwo, scheduleThree);

        Mockito.when(repository.findAll()).thenReturn(schedules);

        List<Schedule> expected = scheduleService.getAllSchedules();

        assertEquals(expected, schedules);
    }

    @Test
    void getScheduleByName() {
        final String name = "test";
        final Schedule schedule = new Schedule("123", "test", "test desc", LocalDateTime.now(), null, null, null);

        Mockito.when(repository.findScheduleByName(name)).thenReturn(Optional.of(schedule));

        final Schedule expected = scheduleService.getScheduleByName(name);

        assertNotNull(expected);
    }

    @Test
    void getScheduleById() {
        final String id = "123";
        final Schedule schedule = new Schedule("123", "test", "test desc", LocalDateTime.now(), null, null, null);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(schedule));

        final Schedule expected = scheduleService.getScheduleById(id);

        assertNotNull(expected);
    }

    @Test
    void updateSchedule() {
        final String id = "123";
        final Schedule schedule = new Schedule("123", "test", "test desc", LocalDateTime.now(), null, null, null);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(schedule));
        Mockito.when(repository.save(schedule)).thenReturn(schedule);

        final Schedule expected = scheduleService.updateSchedule(schedule, schedule.getId());

        assertNotNull(expected);
        Mockito.verify(repository).save(Mockito.any(Schedule.class));
    }

    @Test
    void deleteSchedule() {
        final Schedule schedule = new Schedule("123", "test", "test desc", LocalDateTime.now(), null, null, null);

        Mockito.when(repository.findById(schedule.getId())).thenReturn(Optional.of(schedule));
        scheduleService.deleteSchedule(schedule.getId());

        Mockito.verify(repository, Mockito.times(1)).findById(schedule.getId());
        Mockito.verify(repository, Mockito.times(1)).delete(schedule);
    }

    @Test
    void openSchedule() {
        final Schedule schedule = new Schedule("123","test", "testDesc", LocalDateTime.now(), null, null, null);

        Mockito.when(repository.findById(schedule.getId())).thenReturn(Optional.of(schedule));
        Mockito.when(repository.save(schedule)).thenReturn(schedule);

        scheduleService.openSchedule(schedule, schedule.getId());

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Schedule.class));
    }

    @Test
    void registerAssociateVote() {
        final Votes vote = new Votes("1234567", VoteEnum.SIM);
        final Schedule schedule = new Schedule("123","test", "testDesc", LocalDateTime.now(), null, null, null);

        Mockito.when(repository.findById(schedule.getId())).thenReturn(Optional.of(schedule));
        Mockito.when(repository.save(schedule)).thenReturn(schedule);

        scheduleService.registerAssociateVote(vote, schedule.getId());

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Schedule.class));
    }
}