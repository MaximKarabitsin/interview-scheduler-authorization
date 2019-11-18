package com.netcracker.interviewschedulerauthorization.services.impl;

import com.netcracker.interviewschedulerauthorization.dao.RuleRepository;
import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.exceptions.NotFoundException;
import com.netcracker.interviewschedulerauthorization.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleRepository ruleRepository;


    @Override
    public List<Rule> getAll() {
        return ruleRepository.findAll();
    }

    @Override
    public Set<Rule> getByPolicy(Policy policy) {
        return policy.getRules().stream()
                .map(Rule::getId)
                .distinct()
                .map(ruleRepository::findById)
                .map(x -> x.orElseThrow(BadRequestException::new))
                .collect(Collectors.toSet());
    }

    @Override
    public Rule getById(String id) {
        if (!id.matches("[0-9]+")) throw new NotFoundException();
        return ruleRepository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
    }

    @Override
    public void add(Rule rule) {
        rule.setId(null);
        ruleRepository.save(rule);
    }

    @Override
    public void updateById(String id, Rule rule) {
        ruleRepository.save(getById(id).update(rule));
    }

    @Override
    public void deleteById(String id) {
        ruleRepository.delete(getById(id));
    }
}
