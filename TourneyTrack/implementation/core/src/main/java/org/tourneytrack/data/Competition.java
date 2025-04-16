package org.tourneytrack.data;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Competition {
    private Long id;
    private String name;
    private String description;

    private User gameMaster;
    private List<User> participants;

    private Date deadline;
    private boolean inProgress;

    private RuleSet ruleSet;
    private ScoreBoard scoreBoard;
}