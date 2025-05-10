package org.tourneytrack.dal.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.tourneytrack.dal.entity.CompetitionEntity;
import org.tourneytrack.dal.entity.RuleEntity;
import org.tourneytrack.dal.entity.RuleSetEntity;
import org.tourneytrack.impl.data.Competition;

@Mapper(componentModel = "spring")
public interface CompetitionEntityMapper {

    CompetitionEntity toEntity(Competition model);
    Competition toModel(CompetitionEntity entity);


    @AfterMapping
    default void setRuleSetInRules(@MappingTarget RuleSetEntity entity) {
        if (entity.getRules() != null) {
            for (RuleEntity rule : entity.getRules()) {
                rule.setRuleSet(entity);
            }
        }
    }
    

}