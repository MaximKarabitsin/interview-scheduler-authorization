package com.netcracker.interviewschedulerauthorization.services.impl;

import com.netcracker.interviewschedulerauthorization.dao.RuleRepository;
import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.exceptions.NotFoundException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;

    @Autowired
    public RuleServiceImpl(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public JSONResponse getByPageable(Pageable pageable) {
        Page<Rule> page = ruleRepository.findAll(pageable);
        return new JSONResponse(page.getTotalElements(), page.getContent());
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
    public Rule getById(long id) {
        return ruleRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void add(Rule rule) {
        rule.setId(null);
        ruleRepository.save(rule);
    }

    @Override
    public void updateById(long id, Rule rule) {
        ruleRepository.save(getById(id).update(rule));
    }

    @Override
    public void deleteById(long id) {
        ruleRepository.delete(getById(id));
    }
}
