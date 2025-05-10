package org.tourneytrack.intf.dto.request;

import lombok.Data;
import org.tourneytrack.intf.dto.data.RuleSetDto;

import java.util.Date;
import java.util.List;

@Data
public class CreateCompetitionRequest {
    private String name;
    private String description;
    private Date deadline;
    private Long gameMasterId;
    private List<Long> ruleSetIds;
}
