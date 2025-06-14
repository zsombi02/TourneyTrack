package org.tourneytrack.impl.controller;

import org.springframework.web.bind.annotation.*;
import org.tourneytrack.intf.controller.PublicCompetitionControllerIntf;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.data.SubmissionDto;

import java.util.List;

@RestController
public class PublicCompetitionController extends AbstractController implements PublicCompetitionControllerIntf {

    public List<CompetitionDto> getAllCompetitions() {
        return competitionService.listAll();
    }

    public CompetitionDto getCompetitionById(@PathVariable Long id) {
        return competitionService.getById(id);
    }

    public List<ScoreEntryDto> getScoreBoard(@PathVariable Long id) {
        return competitionService.getScoreBoard(id);
    }

    public List<SubmissionDto> getSubmissions(@PathVariable Long id) {
        return submissionService.getByCompetition(id);
    }
}
