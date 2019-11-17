package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.Rule;

import java.util.List;

public interface RuleService {
    List<Rule> getAll();

    Rule getById(String id);

    void add(Rule rule);

    void updateById(String id, Rule rule);

    void deleteById(String id);
}
