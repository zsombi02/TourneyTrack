package org.tourneytrack.intf.controller;

import org.tourneytrack.intf.dto.data.ScoreBoardDto;

public interface ScoreBoardController {
    ScoreBoardDto getById(Long id);
}
