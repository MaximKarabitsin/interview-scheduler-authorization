package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.PolicySetService;
import com.netcracker.interviewschedulerauthorization.utils.Parser;
import com.netcracker.interviewschedulerauthorization.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/policyset")
public class PolicySetRESTController {

    @Autowired
    private PolicySetService policySetService;

    @GetMapping
    public JSONResponse getAll(@RequestParam(required = false) String page,
                               @RequestParam(required = false) String size,
                               @RequestParam(required = false) String sortBy,
                               @RequestParam(required = false) String sortDesc) {
        if (page == null) {
            return policySetService.getAll();
        } else {
            int pageNumber = Parser.toInteger(page).orElseThrow(BadRequestException::new);
            int pageSize = Parser.toInteger(size).orElseThrow(BadRequestException::new);
            boolean sortDescending = false;
            if (--pageNumber < 0) throw new BadRequestException();
            if (sortBy != null && !sortBy.isEmpty()) {
                sortDescending = Parser.toBoolean(sortDesc).orElseThrow(BadRequestException::new);
            }
            return policySetService.getByPageAndSort(pageNumber, pageSize, sortBy, sortDescending);
        }
    }

    @GetMapping("{id}")
    public PolicySet getById(@PathVariable String id) {
        PolicySet p = policySetService.getById(Parser.toLong(id).orElseThrow(BadRequestException::new));
        return p;
    }

    @PostMapping
    public void add(@RequestBody PolicySet policySet) {
        if (Validator.isEmpty(policySet.getName())) throw new BadRequestException();
        if (Validator.isEmpty(policySet.getDescription())) throw new BadRequestException();
        if (Validator.isEmpty(policySet.getTarget())) throw new BadRequestException();
        if (policySet.getAlgorithm() == null) throw new BadRequestException();
        if (policySet.getPolicies() == null || policySet.getPolicies().isEmpty()) throw new BadRequestException();
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

