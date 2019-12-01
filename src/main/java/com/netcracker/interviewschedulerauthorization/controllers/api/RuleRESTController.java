package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.RuleService;
import com.netcracker.interviewschedulerauthorization.utils.Parser;
import com.netcracker.interviewschedulerauthorization.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        } else {
            int pageNumber = Parser.toInteger(page).orElseThrow(BadRequestException::new);
            int pageSize = Parser.toInteger(size).orElseThrow(BadRequestException::new);
            boolean sortDescending = false;
            if (--pageNumber < 0) throw new BadRequestException();
            if (sortBy != null && !sortBy.isEmpty()) {
                sortDescending = Parser.toBoolean(sortDesc).orElseThrow(BadRequestException::new);
            }
            return ruleService.getByPageAndSort(pageNumber, pageSize, sortBy, sortDescending);
        }
    }


    @GetMapping("{id}")
    public Rule getById(@PathVariable String id) {
        return ruleService.getById(Parser.toLong(id).orElseThrow(BadRequestException::new));
    }

    @PostMapping
    public void add(@RequestBody Rule rule) {
        if (Validator.isEmpty(rule.getName())) throw new BadRequestException();
        if (Validator.isEmpty(rule.getDescription())) throw new BadRequestException();
        if (Validator.isEmpty(rule.getTarget())) throw new BadRequestException();
        if (Validator.isEmpty(rule.getCondition())) throw new BadRequestException();
        if (rule.getEffect() == null) throw new BadRequestException();
        ruleService.add(rule);
    }

    @PutMapping("{id}")
    public void updateById(@PathVariable String id, @RequestBody Rule rule) {
        ruleService.updateById(Parser.toLong(id).orElseThrow(BadRequestException::new), rule);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        ruleService.deleteById(Parser.toLong(id).orElseThrow(BadRequestException::new));
    }
}

