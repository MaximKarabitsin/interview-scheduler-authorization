package com.netcracker.interviewschedulerauthorization.services.impl;

import com.netcracker.interviewschedulerauthorization.dao.PolicySetRepository;
import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.exceptions.NotFoundException;
import com.netcracker.interviewschedulerauthorization.services.PolicyService;
import com.netcracker.interviewschedulerauthorization.services.PolicySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicySetServiceImpl implements PolicySetService {

    @Autowired
    private PolicySetRepository policySetRepository;

    @Autowired
    private PolicyService policyService;

    @Override
    public List<PolicySet> getAll() {
        return policySetRepository.findAll();
    }

    @Override
    public PolicySet getById(String id) {
        if (!id.matches("[0-9]+")) throw new NotFoundException();
        return policySetRepository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
    }

    @Override
    public void add(PolicySet policySet) {
        policySet.setPolicies(policyService.getByPolicySet(policySet));
        policySet.setId(null);
        policySetRepository.save(policySet);
    }

    @Override
    public void updateById(String id, PolicySet policySet) {
        policySet.setPolicies(policyService.getByPolicySet(policySet));
        policySetRepository.save(getById(id).update(policySet));
    }

    @Override
    public void deleteById(String id) {
        policySetRepository.delete(getById(id));
    }
}
