package org.tourneytrack.impl.business.service.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tourneytrack.impl.business.service.CompetitionService;
import org.tourneytrack.impl.dao.CompetitionDao;
import org.tourneytrack.impl.data.Competition;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CompetitionDeadlineJob {

    private final CompetitionDao competitionDao;
    private final CompetitionService competitionService;

    @Scheduled(cron = "0 0 0 * * *")
    public void closeExpiredCompetitions() {
        Date now = new Date();
        List<Competition> all = competitionDao.findAll();
        int closed = 0;

        for (Competition comp : all) {
            if (comp.isInProgress() && comp.getDeadline() != null && !comp.getDeadline().after(now)) {
                competitionService.stopCompetition(comp.getId());
                closed++;
                log.info("Competition closed by job: id={}, name={}, deadline={}", comp.getId(), comp.getName(), comp.getDeadline());
            }
        }
        log.info("CompetitionDeadlineJob finished, closed competitions: {}", closed);
    }
}