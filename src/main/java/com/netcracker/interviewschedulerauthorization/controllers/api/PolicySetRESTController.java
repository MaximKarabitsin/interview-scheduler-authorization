package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.services.PolicySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policy_set")
public class PolicySetRESTController {

    @Autowired
    private PolicySetService policySetService;

    @GetMapping
    public List<PolicySet> getAll() {
        return policySetService.getAll();
    }

    @GetMapping("{id}")
    public PolicySet getById(@PathVariable String id) {
        PolicySet p = policySetService.getById(id);
        //p.getRules();
        return p;
    }

    @PostMapping
    public void add(@RequestBody PolicySet policySet) {
        policySetService.add(policySet);
    }

    @PutMapping("{id}")
    public void updateById(@PathVariable String id, @RequestBody PolicySet policySet) {
        policySetService.updateById(id, policySet);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        policySetService.deleteById(id);
    }

}
