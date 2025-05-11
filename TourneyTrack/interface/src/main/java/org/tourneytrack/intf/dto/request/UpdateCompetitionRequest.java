package org.tourneytrack.intf.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UpdateCompetitionRequest {
    private String name;
    private String description;
    private Date deadline;
}
