package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;

import java.util.List;
import java.util.Set;

public interface RuleService {
    JSONResponse getAll();

    JSONResponse getByPageAndSort(String page, String size, String sortBy, String sortDesc);

    Set<Rule> getByPolicy(Policy policy);

    Rule getById(String id);

    void add(Rule rule);

    void updateById(String id, Rule rule);

    void deleteById(String id);
}
