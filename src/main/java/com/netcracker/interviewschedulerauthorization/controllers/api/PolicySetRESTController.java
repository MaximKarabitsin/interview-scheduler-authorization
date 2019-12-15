package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.PolicySetService;
import com.netcracker.interviewschedulerauthorization.utils.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/policyset")
public class PolicySetRESTController {

    private final PolicySetService policySetService;

    @Autowired
    public PolicySetRESTController(PolicySetService policySetService) {
        this.policySetService = policySetService;
    }

    @GetMapping
    public JSONResponse getAll(@PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return policySetService.getByPageable(pageable);
    }

    @GetMapping("{id}")
    public PolicySet getById(@PathVariable String id) {
        return policySetService.getById(Parser.toLong(id).orElseThrow(BadRequestException::new));
    }

    @PostMapping
    public void add(@Valid @RequestBody PolicySet policySet) {
        policySetService.add(policySet);
    }

    @PutMapping("{id}")
    public void updateById(@PathVariable String id, @RequestBody PolicySet policySet) {
        policySetService.updateById(Parser.toLong(id).orElseThrow(BadRequestException::new), policySet);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        policySetService.deleteById(Parser.toLong(id).orElseThrow(BadRequestException::new));
    }

}

