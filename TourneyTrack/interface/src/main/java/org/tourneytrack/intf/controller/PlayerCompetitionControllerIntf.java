package org.tourneytrack.intf.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.request.SubmitScoreRequest;

import java.util.List;

@RequestMapping("/api/player/competitions")
public interface PlayerCompetitionControllerIntf {

    @PostMapping("/{id}/join")
    void joinCompetition(@PathVariable Long id, Authentication authentication);

    @PostMapping("/{id}/leave")
    void leaveCompetition(@PathVariable Long id, Authentication authentication);

    @GetMapping
    List<CompetitionDto> listMyCompetitions(Authentication authentication);

    @PostMapping("/{id}/submit")
    void submitScore(@RequestBody SubmitScoreRequest request, @PathVariable Long id, Authentication authentication);
}