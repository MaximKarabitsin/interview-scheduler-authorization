package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import org.springframework.data.domain.Pageable;

public interface PolicySetService {
    JSONResponse getByPageable(Pageable pageable);


    PolicySet getById(long id);

    void add(PolicySet policySet);

    void updateById(long id, PolicySet policySet);

    void deleteById(long id);
}
