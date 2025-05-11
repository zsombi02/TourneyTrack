package org.tourneytrack.intf.controller;

import org.springframework.security.core.Authentication;
import org.tourneytrack.intf.dto.data.SubmissionDto;

import java.util.List;

public interface PlayerSubmissionControllerIntf {

    List<SubmissionDto> getMySubmissions(Authentication authentication);
}
