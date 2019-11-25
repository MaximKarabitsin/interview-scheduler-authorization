package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.PolicySet;

import java.util.List;

public interface PolicySetService {
    List<PolicySet> getAll();

    PolicySet getById(String id);

    void add(PolicySet policySet);

    void updateById(String id, PolicySet policySet);

    void deleteById(String id);
}
