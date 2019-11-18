package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.services.PolicyService;
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
    public List<Policy> getAll() {
        return policyService.getAll();
    }

    @GetMapping("{id}")
    public Policy getById(@PathVariable String id) {
        return policyService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody Policy policy) {
        policyService.add(policy);
    }

    @PutMapping("{id}")
    public void updateById(@PathVariable String id, @RequestBody Policy policy) {
        policyService.updateById(id, policy);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        policyService.deleteById(id);
    }
}

