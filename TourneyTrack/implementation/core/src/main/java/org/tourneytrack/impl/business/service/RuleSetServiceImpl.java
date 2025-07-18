package org.tourneytrack.impl.business.service;

import org.springframework.stereotype.Component;
import org.tourneytrack.impl.data.Rule;
import org.tourneytrack.impl.data.RuleSet;
import org.tourneytrack.intf.dto.data.RuleSetDto;
import org.tourneytrack.intf.dto.request.CreateRuleRequest;
import org.tourneytrack.intf.dto.request.CreateRuleSetRequest;
import org.tourneytrack.intf.dto.request.UpdateRuleRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RuleSetServiceImpl extends AbstractServiceBase implements RuleSetService {


    @Override
    public RuleSetDto createRuleSet(CreateRuleSetRequest request) {
        validationService.validateRuleSetName(request.getName());

        RuleSet ruleSet = new RuleSet();
        ruleSet.setName(request.getName());

        if (request.getRules() != null && !request.getRules().isEmpty()) {
            List<Rule> rules = request.getRules().stream().map(r -> {
                validationService.validateRuleFields(r.getName(), r.getPoints());

                Rule rule = new Rule();
                rule.setName(r.getName());
                rule.setDescription(r.getDescription());
                rule.setPoints(r.getPoints());
                return rule;
            }).collect(Collectors.toList());
            ruleSet.setRules(rules);
        } else {
            ruleSet.setRules(List.of());
        }

        return ruleSetMapper.toDto(ruleSetDao.save(ruleSet));
    }

    @Override
    public RuleSetDto getRuleSetById(Long id) {
        Optional<RuleSet> ruleSet = ruleSetDao.findById(id);
        return ruleSet.map(ruleSetMapper::toDto).orElse(null);
    }

    @Override
    public List<RuleSetDto> listAll() {
        return ruleSetDao.findAll().stream()
                .map(ruleSetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RuleSetDto addRule(Long ruleSetId, CreateRuleRequest request) {
        validationService.validateRuleSetExists(ruleSetId);
        validationService.validateRuleFields(request.getName(), request.getPoints());

        Rule rule = new Rule();
        rule.setName(request.getName());
        rule.setDescription(request.getDescription());
        rule.setPoints(request.getPoints());

        // RÉGI:
        // ruleDao.save(rule, ruleSetId);
        // return getRuleSetById(ruleSetId);

        // ÚJ:
        RuleSet ruleSet = ruleSetDao.addRule(ruleSetId, rule);
        return ruleSetMapper.toDto(ruleSet);
    }

    @Override
    public RuleSetDto updateRule(Long ruleSetId, Long ruleId, UpdateRuleRequest request) {
        validationService.validateRuleSetExists(ruleSetId);
        validationService.validateRuleExists(ruleId);
        validationService.validateRuleFields(request.getName(), request.getPoints());

        Rule rule = new Rule();
        rule.setId(ruleId);
        rule.setName(request.getName());
        rule.setDescription(request.getDescription());
        rule.setPoints(request.getPoints());

        RuleSet ruleSet = ruleSetDao.updateRule(ruleSetId, rule);
        return ruleSetMapper.toDto(ruleSet);
    }

    @Override
    public RuleSetDto deleteRule(Long ruleSetId, Long ruleId) {
        validationService.validateRuleSetExists(ruleSetId);
        validationService.validateRuleExists(ruleId);
        validationService.validateRuleNotUsedInSubmissions(ruleId);

        RuleSet ruleSet = ruleSetDao.removeRule(ruleSetId, ruleId);
        return ruleSetMapper.toDto(ruleSet);
    }

    @Override
    public RuleSetDto updateRuleSet(Long id, CreateRuleSetRequest request) {
        validationService.validateRuleSetExists(id);
        validationService.validateRuleSetName(request.getName());

        RuleSet ruleSet = ruleSetDao.findById(id).orElseThrow();
        ruleSet.setName(request.getName());

        return ruleSetMapper.toDto(ruleSetDao.save(ruleSet));
    }

    @Override
    public void deleteRuleSet(Long id) {
        validationService.validateRuleSetExists(id);
        validationService.validateRuleSetNotUsedInCompetitions(id);
        ruleSetDao.deleteById(id);
    }
}
