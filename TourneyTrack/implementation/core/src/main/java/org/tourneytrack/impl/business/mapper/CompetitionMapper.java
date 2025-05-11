package org.tourneytrack.impl.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tourneytrack.impl.data.*;
import org.tourneytrack.intf.dto.data.*;
import org.tourneytrack.intf.dto.request.CreateCompetitionRequest;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    CompetitionDto toDto(Competition competition);

    Competition toModel(CompetitionDto competitionDto);

    RuleSetDto toDto(RuleSet ruleSet);

    RuleSet toModel(RuleSetDto ruleSetDto);

    RuleDto toDto(Rule rule);

    Rule toModel(RuleDto ruleDto);

    UserDto toDto(User user);

    User toModel(UserDto userDto);

    ScoreEntryDto toDto(ScoreEntry scoreEntry);

    ScoreEntry toModel(ScoreEntryDto scoreEntryDto);

    // ÚJ: CreateCompetitionRequest → Competition
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "participants", ignore = true)
    @Mapping(target = "inProgress", constant = "true")
    @Mapping(target = "ruleSets", ignore = true)
    Competition fromCreateCompetitionRequest(CreateCompetitionRequest request);
}
