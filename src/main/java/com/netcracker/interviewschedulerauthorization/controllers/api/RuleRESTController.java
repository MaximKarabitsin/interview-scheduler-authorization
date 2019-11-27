package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rule")
public class RuleRESTController {

    @Autowired
    private RuleService ruleService;


    @GetMapping
    public JSONResponse getAll(@RequestParam(required = false) String page,
                               @RequestParam(required = false) String size,
                               @RequestParam(required = false) String sortBy,
                               @RequestParam(required = false) String sortDesc) {
        if (page == null) {
            return ruleService.getAll();
        }
        else {
            return ruleService.getByPageAndSort(page, size, sortBy, sortDesc);
        }
    }


    @GetMapping("{id}")
    public Rule getById(@PathVariable String id) {
        return ruleService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody Rule rule) {
        ruleService.add(rule);
    }

    @PutMapping("{id}")
    public void updateById(@PathVariable String id, @RequestBody Rule rule) {
        ruleService.updateById(id, rule);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        ruleService.deleteById(id);
    }
}

