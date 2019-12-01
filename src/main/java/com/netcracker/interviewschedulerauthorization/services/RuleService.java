package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;

import java.util.Set;

public interface RuleService {
    JSONResponse getAll();

    JSONResponse getByPageAndSort(int page, int size, String sortBy, boolean sortDesc);

    Set<Rule> getByPolicy(Policy policy);

    Rule getById(long id);

    void add(Rule rule);

    void updateById(long id, Rule rule);

    void deleteById(long id);
}
