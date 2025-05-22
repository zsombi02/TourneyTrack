package org.tourneytrack.intf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.data.ScoreEntryDto;
import org.tourneytrack.intf.dto.data.SubmissionDto;

import java.util.List;

@RequestMapping("/api/public/competitions")
public interface PublicCompetitionControllerIntf {

    @GetMapping
    List<CompetitionDto> getAllCompetitions();

    @GetMapping("/{id}")
    CompetitionDto getCompetitionById(@PathVariable Long id);

    @GetMapping("/{id}/scoreboard")
    List<ScoreEntryDto> getScoreBoard(@PathVariable Long id);

    @GetMapping("/{id}/submissions")
    List<SubmissionDto> getSubmissions(@PathVariable Long id);
}
