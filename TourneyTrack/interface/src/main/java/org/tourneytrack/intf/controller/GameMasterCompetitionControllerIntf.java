package org.tourneytrack.intf.controller;

import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.data.SubmissionDto;
import org.tourneytrack.intf.dto.request.ApproveSubmissionRequest;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;
import org.tourneytrack.intf.dto.request.UpdateCompetitionRequest;

import java.util.List;

public interface GameMasterCompetitionControllerIntf {

    void createCompetition(CreateCompetitionRequest request);

    void updateCompetition(Long id, UpdateCompetitionRequest request);

    void deleteCompetition(Long id);

    void stopCompetition(Long id);

    List<ScoreEntryDto> getScoreBoard(Long id);

    List<SubmissionDto> getSubmissions(Long id);
}
