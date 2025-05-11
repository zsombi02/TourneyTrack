package org.tourneytrack.intf.controller;

import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.data.SubmissionDto;

import java.util.List;

public interface PublicCompetitionControllerIntf {

    List<CompetitionDto> getAllCompetitions();

    CompetitionDto getCompetitionById(Long id);

    List<ScoreEntryDto> getScoreBoard(Long id);

    List<SubmissionDto> getSubmissions(Long id);
}
