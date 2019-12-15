package com.netcracker.interviewschedulerauthorization.services.impl;

import com.netcracker.interviewschedulerauthorization.dao.PolicySetRepository;
import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.entities.Targeter;
import com.netcracker.interviewschedulerauthorization.intities.enums.CombineAlgorithm;
import com.netcracker.interviewschedulerauthorization.model.SecurityAccessContext;
import com.netcracker.interviewschedulerauthorization.services.SecurityAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class SecurityAccessServiceImpl implements SecurityAccessService {

    private static ExpressionParser parser = new SpelExpressionParser();

    private final PolicySetRepository policySetRepository;

    @Autowired
    public SecurityAccessServiceImpl(PolicySetRepository policySetRepository) {
        this.policySetRepository = policySetRepository;
    }

    @Override
    public boolean checkAccess(SecurityAccessContext context) {
        List<PolicySet> policySets = policySetRepository.findAll();
        return checkPolicySets(policySets, context);
    }

    private boolean checkPolicySets(List<PolicySet> list, SecurityAccessContext context) {
        List<Boolean> results = list.stream()
                .filter(x -> checkTarget(x, context))
                .map(x -> checkPolicySet(x, context))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (results.isEmpty())
            return false;
        else
            return CombineAlgorithm.PERMIT_IF_ALL_PERMITTED.combine(results);
    }


    private Boolean checkPolicySet(PolicySet policySet, SecurityAccessContext context) {
        List<Boolean> results = policySet.getPolicies().stream()
                .filter(x -> checkTarget(x, context))
                .map(x -> checkPolicy(x, context))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (results.isEmpty())
            return null;
        else
            return policySet.getAlgorithm().combine(results);
    }

    private Boolean checkPolicy(Policy policy, SecurityAccessContext context) {
        List<Boolean> results = policy.getRules().stream()
                .filter(x -> checkTarget(x, context))
                .map(x -> checkRule(x, context))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (results.isEmpty())
            return null;
        else
            return policy.getAlgorithm().combine(results);
    }

    private Boolean checkRule(Rule rule, SecurityAccessContext context) {
        try {
            return parser.parseExpression(rule.getCondition()).getValue(context, boolean.class) ? rule.getEffect() : !rule.getEffect();
        } catch (Exception e) {
            return null;
        }
    }

    private Boolean checkTarget(Targeter targeter, SecurityAccessContext context) {
        try {
            return parser.parseExpression(targeter.getTarget()).getValue(context, boolean.class);
        } catch (Exception e) {
            return false;
        }
    }
}
