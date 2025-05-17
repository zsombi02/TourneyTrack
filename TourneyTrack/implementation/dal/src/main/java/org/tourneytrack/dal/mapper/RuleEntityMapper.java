package org.tourneytrack.dal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tourneytrack.dal.entity.RuleEntity;
import org.tourneytrack.impl.data.Rule;

@Mapper(componentModel = "spring")
public interface RuleEntityMapper {

    RuleEntity toEntity(Rule rule);

    Rule toModel(RuleEntity entity);
}
