package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PolicyService {
    JSONResponse getByPageable(Pageable pageable);

    Set<Policy> getByPolicySet(PolicySet policySet);

    Policy getById(long id);

    void add(Policy policy);

    void updateById(long id, Policy policy);

    void deleteById(long id);
}
