package com.cooperativism.voting.service;

import com.cooperativism.voting.domain.Schedule;
import com.cooperativism.voting.event.VoteResultQueueProducer;
import com.cooperativism.voting.mapper.VoteResultMessageMapper;
import com.cooperativism.voting.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ScheduleResultServiceImpl implements ScheduleResultService {

    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private VoteResultQueueProducer producer;

    @Autowired
    private VoteResultMessageMapper mapper;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Scheduled(fixedRate = 5000)
    public void sendVoteResultToTheQueue() {
        var schedules = repository.findAllClosedScheduleToSendToQueue();
        log.info("Iniciando a verificação das Pautas fechadas");
        schedules.stream()
                .forEach( schedule -> {
                    if (checkDataToSendMesssage(schedule)) {
                        var voteResultMessage = mapper.toVoteResultMessageResponse(schedule);
                        log.info("Enviando para a fila a Pauta: {}", voteResultMessage);
                        producer.send(voteResultMessage.toString());
                        schedule.setStatus(true);
                        repository.save(schedule);
                    }
                });
        log.info("Verificação de Pautas fechadas finalizado");
    }

    private boolean checkDataToSendMesssage(final Schedule schedule) {
        if (schedule.getEndDate() == null) return false;
        int endDateComparison = schedule.getEndDate().compareTo(LocalDateTime.now());
        boolean nullAndStatusFalse = schedule.getStatus() == null || !schedule.getStatus();
        boolean scheduleIsClosed = endDateComparison < 0;
        boolean scheduleHasVotes = schedule.getVoteResults() != null;
        if (nullAndStatusFalse && scheduleIsClosed && scheduleHasVotes) return true;
        return false;
    }
}
