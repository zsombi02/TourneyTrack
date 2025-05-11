package org.tourneytrack.intf.dto.data;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CompetitionDto {
    private Long id;
    private String name;
    private String description;
    private UserDto gameMaster;
    private List<UserDto> participants;
    private Date deadline;
    private boolean inProgress;
    private List<RuleSetDto> ruleSets;
}