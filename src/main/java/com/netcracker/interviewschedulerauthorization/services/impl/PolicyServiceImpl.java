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

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private RuleService ruleService;

    @Override
    public JSONResponse getAll() {
        Page<Policy> page = policyRepository.findAll(PageRequest.of(0, Integer.MAX_VALUE, Sort.by("id").descending()));
        return new JSONResponse(page.getTotalElements(), page.getContent());
    }

    @Override
    public JSONResponse getByPageAndSort(int page, int size, String sortBy, boolean sortDesc) {
        Sort sort;
        if (sortBy != null && !sortBy.isEmpty()) {
            sort = Sort.by(sortBy);
            if (sortDesc) {
                sort = sort.descending();
            }
        } else {
            sort = Sort.by("id").descending();
        }
        Page<Policy> pageRules = policyRepository.findAll(PageRequest.of(page, size, sort));
        return new JSONResponse(pageRules.getTotalElements(), pageRules.getContent());
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
