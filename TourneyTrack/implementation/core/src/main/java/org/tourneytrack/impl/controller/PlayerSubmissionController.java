package org.tourneytrack.impl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tourneytrack.intf.controller.PlayerSubmissionControllerIntf;
import org.tourneytrack.intf.dto.data.SubmissionDto;

import java.util.List;

@RestController
@RequestMapping("/api/player/submissions")
@PreAuthorize("hasRole('PLAYER')")
public class PlayerSubmissionController extends AbstractController implements PlayerSubmissionControllerIntf {

    @GetMapping
    public List<SubmissionDto> getMySubmissions(Authentication authentication) {
        Long userId = userService.getByEmail(authentication.getName()).getId();
        return submissionService.getByUser(userId);
    }
}
