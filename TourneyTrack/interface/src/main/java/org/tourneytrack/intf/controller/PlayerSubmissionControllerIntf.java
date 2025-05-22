package org.tourneytrack.intf.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tourneytrack.intf.dto.data.SubmissionDto;

import java.util.List;

@RequestMapping("/api/player/submissions")
public interface PlayerSubmissionControllerIntf {

    @GetMapping
    List<SubmissionDto> getMySubmissions(Authentication authentication);
}
