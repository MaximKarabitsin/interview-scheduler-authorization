package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.PolicyService;
import com.netcracker.interviewschedulerauthorization.utils.Parser;
import com.netcracker.interviewschedulerauthorization.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/policy")
public class PolicyRESTController {

    @Autowired
    private PolicyService policyService;

    @GetMapping
    public JSONResponse getAll(@RequestParam(required = false) String page,
                               @RequestParam(required = false) String size,
                               @RequestParam(required = false) String sortBy,
                               @RequestParam(required = false) String sortDesc) {
        if (page == null) {
            return policyService.getAll();
        } else {
            int pageNumber = Parser.toInteger(page).orElseThrow(BadRequestException::new);
            int pageSize = Parser.toInteger(size).orElseThrow(BadRequestException::new);
            boolean sortDescending = false;
            if (--pageNumber < 0) throw new BadRequestException();
            if (sortBy != null && !sortBy.isEmpty()) {
                sortDescending = Parser.toBoolean(sortDesc).orElseThrow(BadRequestException::new);
            }
            return policyService.getByPageAndSort(pageNumber, pageSize, sortBy, sortDescending);
        }
    }
    @GetMapping("{id}")
    public Policy getById(@PathVariable String id) {
        Policy p = policyService.getById(Parser.toLong(id).orElseThrow(BadRequestException::new));
        return p;
    }

    @PostMapping
    public void add(@RequestBody Policy policy) {
        if (Validator.isEmpty(policy.getName())) throw new BadRequestException();
        if (Validator.isEmpty(policy.getDescription())) throw new BadRequestException();
        if (Validator.isEmpty(policy.getTarget())) throw new BadRequestException();
        if (policy.getAlgorithm() == null)throw new BadRequestException();
        if (policy.getRules() == null || policy.getRules().isEmpty())throw new BadRequestException();
        policyService.add(policy);
    }

    @PutMapping("{id}")
    public void updateById(@PathVariable String id, @RequestBody Policy policy) {
        policyService.updateById(Parser.toLong(id).orElseThrow(BadRequestException::new), policy);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        policyService.deleteById(Parser.toLong(id).orElseThrow(BadRequestException::new));
    }
}

