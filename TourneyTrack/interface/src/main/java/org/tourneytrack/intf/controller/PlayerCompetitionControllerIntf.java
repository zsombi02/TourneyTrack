package org.tourneytrack.intf.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.tourneytrack.intf.dto.data.CompetitionDto;
import org.tourneytrack.intf.dto.request.SubmitScoreRequest;

import java.util.List;

public interface PlayerCompetitionControllerIntf {

    void joinCompetition(@PathVariable Long id, Authentication authentication);

    void leaveCompetition(@PathVariable Long id, Authentication authentication);

    public List<CompetitionDto> listMyCompetitions(Authentication authentication);

    public void submitScore(@RequestBody SubmitScoreRequest request, @PathVariable Long id, Authentication authentication);
}
