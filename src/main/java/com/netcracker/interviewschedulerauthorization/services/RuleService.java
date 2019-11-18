package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.Rule;

import java.util.List;
import java.util.Set;

public interface RuleService {
    List<Rule> getAll();

    Set<Rule> getByPolicy(Policy policy);

    Rule getById(String id);

    void add(Rule rule);

    void updateById(String id, Rule rule);

    void deleteById(String id);
}
