package com.netcracker.interviewschedulerauthorization.services.impl;

import com.netcracker.interviewschedulerauthorization.dao.PolicyRepository;
import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.exceptions.NotFoundException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.PolicyService;
import com.netcracker.interviewschedulerauthorization.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final RuleService ruleService;

    @Autowired
    public PolicyServiceImpl(PolicyRepository policyRepository, RuleService ruleService) {
        this.policyRepository = policyRepository;
        this.ruleService = ruleService;
    }


    @Override
    public JSONResponse getByPageable(Pageable pageable) {
        Page<Policy> page = policyRepository.findAll(pageable);
        return new JSONResponse(page.getTotalElements(), page.getContent());
    }


    @Override
    public Set<Policy> getByPolicySet(PolicySet policySet) {
        return policySet.getPolicies().stream()
                .map(Policy::getId)
                .distinct()
                .map(policyRepository::findById)
                .map(x -> x.orElseThrow(BadRequestException::new))
                .collect(Collectors.toSet());
    }

    @Override
    public Policy getById(long id) {
        return policyRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void add(Policy policy) {
        policy.setRules(ruleService.getByPolicy(policy));
        policy.setId(null);
        policyRepository.save(policy);
    }

    @Override
    public void updateById(long id, Policy policy) {
        policy.setRules(ruleService.getByPolicy(policy));
        policyRepository.save(getById(id).update(policy));
    }

    @Override
    public void deleteById(long id) {
        policyRepository.delete(getById(id));
    }


}
