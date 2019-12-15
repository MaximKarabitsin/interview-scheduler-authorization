package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface RuleService {
    JSONResponse getByPageable(Pageable pageable);

    Set<Rule> getByPolicy(Policy policy);

    Rule getById(long id);

    void add(Rule rule);

    void updateById(long id, Rule rule);

    void deleteById(long id);
}
