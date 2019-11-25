package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.PolicySet;

import java.util.List;
import java.util.Set;

public interface PolicyService {
    List<Policy> getAll();

    Set<Policy> getByPolicySet(PolicySet policySet);

    Policy getById(String id);

    void add(Policy policy);

    void updateById(String id, Policy policy);

    void deleteById(String id);
}
