package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.PolicyService;
import com.netcracker.interviewschedulerauthorization.utils.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/policy")
public class PolicyRESTController {

    private final PolicyService policyService;

    @Autowired
    public PolicyRESTController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public JSONResponse getAll(@PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return policyService.getByPageable(pageable);
    }

    @GetMapping("{id}")
    public Policy getById(@PathVariable String id) {
        return policyService.getById(Parser.toLong(id).orElseThrow(BadRequestException::new));
    }

    @PostMapping
    public void add(@Valid @RequestBody Policy policy) {
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

