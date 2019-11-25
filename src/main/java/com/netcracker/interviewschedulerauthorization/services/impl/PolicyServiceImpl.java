package com.netcracker.interviewschedulerauthorization.services.impl;

import com.netcracker.interviewschedulerauthorization.dao.PolicyRepository;
import com.netcracker.interviewschedulerauthorization.dao.RuleRepository;
import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.exceptions.NotFoundException;
import com.netcracker.interviewschedulerauthorization.services.PolicyService;
import com.netcracker.interviewschedulerauthorization.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private RuleService ruleService;

    @Override
    public List<Policy> getAll() {
        return policyRepository.findAll();
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
    public Policy getById(String id) {
        if (!id.matches("[0-9]+")) throw new NotFoundException();
        return policyRepository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
    }

    @Override
    public void add(Policy policy) {
        policy.setRules(ruleService.getByPolicy(policy));
        policy.setId(null);
        policyRepository.save(policy);
    }

    @Override
    public void updateById(String id, Policy policy) {
        policy.setRules(ruleService.getByPolicy(policy));
        policyRepository.save(getById(id).update(policy));
    }

    @Override
    public void deleteById(String id) {
        policyRepository.delete(getById(id));
    }


}
