package org.tourneytrack.impl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.tourneytrack.intf.controller.PlayerCompetitionControllerIntf;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.request.SubmitScoreRequest;

import java.util.List;

@RestController
@PreAuthorize("hasRole('PLAYER')")
public class PlayerCompetitionController extends AbstractController implements PlayerCompetitionControllerIntf {

    public void joinCompetition(@PathVariable Long id, Authentication authentication) {
        Long userId = userService.getByEmail(authentication.getName()).getId();
        competitionService.joinCompetition(id, userId);
    }

    public void leaveCompetition(@PathVariable Long id, Authentication authentication) {
        Long userId = userService.getByEmail(authentication.getName()).getId();
        competitionService.leaveCompetition(id, userId);
    }

    public List<CompetitionDto> listMyCompetitions(Authentication authentication) {
        Long userId = userService.getByEmail(authentication.getName()).getId();
        return competitionService.listForUser(userId);
    }

    public void submitScore(@RequestBody SubmitScoreRequest request, @PathVariable Long id, Authentication authentication) {
        Long userId = userService.getByEmail(authentication.getName()).getId();
        request.setUserId(userId); // ha van ilyen mező
        request.setCompetitionId(id);
        submissionService.submitScore(request);
    }
}

