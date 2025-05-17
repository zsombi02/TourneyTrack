package org.tourneytrack.dal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.tourneytrack.dal.entity.RuleEntity;
import org.tourneytrack.dal.entity.RuleSetEntity;
import org.tourneytrack.impl.data.Rule;
import org.tourneytrack.impl.data.RuleSet;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RuleSetEntityMapper {

    default RuleSet toModel(RuleSetEntity entity) {
        if (entity == null) return null;

        RuleSet model = new RuleSet();
        model.setId(entity.getId());
        model.setName(entity.getName());

        if (entity.getRules() != null) {
            List<Rule> rules = entity.getRules().stream().map(e -> {
                Rule r = new Rule();
                r.setId(e.getId());
                r.setName(e.getName());
                r.setDescription(e.getDescription());
                r.setPoints(e.getPoints());
                r.setRepetitions(e.getRepetitions());
                return r;
            }).collect(Collectors.toList());
            model.setRules(rules);
        }

        return model;
    }

    default RuleSetEntity toEntity(RuleSet model) {
        if (model == null) return null;

        RuleSetEntity entity = new RuleSetEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());

        if (model.getRules() != null) {
            List<RuleEntity> ruleEntities = model.getRules().stream().map(r -> {
                RuleEntity re = new RuleEntity();
                re.setId(r.getId());
                re.setName(r.getName());
                re.setDescription(r.getDescription());
                re.setPoints(r.getPoints());
                re.setRepetitions(r.getRepetitions());
//                re.setRuleSet(entity);
                return re;
            }).collect(Collectors.toList());
            entity.setRules(ruleEntities);
        }

        return entity;
    }
}
