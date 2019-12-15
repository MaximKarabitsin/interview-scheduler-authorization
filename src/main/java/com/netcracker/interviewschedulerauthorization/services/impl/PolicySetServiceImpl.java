package com.netcracker.interviewschedulerauthorization.services.impl;

import com.netcracker.interviewschedulerauthorization.dao.PolicySetRepository;
import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.exceptions.NotFoundException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.PolicyService;
import com.netcracker.interviewschedulerauthorization.services.PolicySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PolicySetServiceImpl implements PolicySetService {

    private final PolicySetRepository policySetRepository;
    private final PolicyService policyService;

    @Autowired
    public PolicySetServiceImpl(PolicySetRepository policySetRepository, PolicyService policyService) {
        this.policySetRepository = policySetRepository;
        this.policyService = policyService;
    }

    @Override
    public JSONResponse getByPageable(Pageable pageable) {
        Page<PolicySet> page = policySetRepository.findAll(pageable);
        return new JSONResponse(page.getTotalElements(), page.getContent());
    }


    @Override
    public PolicySet getById(long id) {
        return policySetRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void add(PolicySet policySet) {
        policySet.setPolicies(policyService.getByPolicySet(policySet));
        policySet.setId(null);
        policySetRepository.save(policySet);
    }

    @Override
    public void updateById(long id, PolicySet policySet) {
        policySet.setPolicies(policyService.getByPolicySet(policySet));
        policySetRepository.save(getById(id).update(policySet));
    }

    @Override
    public void deleteById(long id) {
        policySetRepository.delete(getById(id));
    }
}
