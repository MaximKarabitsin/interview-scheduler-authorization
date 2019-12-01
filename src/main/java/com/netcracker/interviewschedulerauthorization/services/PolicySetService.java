package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;

public interface PolicySetService {
    JSONResponse getAll();

    JSONResponse getByPageAndSort(int page, int size, String sortBy, boolean sortDesc);

    PolicySet getById(long id);

    void add(PolicySet policySet);

    void updateById(long id, PolicySet policySet);

    void deleteById(long id);
}
