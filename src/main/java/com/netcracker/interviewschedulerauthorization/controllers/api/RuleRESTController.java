package com.netcracker.interviewschedulerauthorization.controllers.api;

import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.RuleService;
import com.netcracker.interviewschedulerauthorization.utils.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/rule")
public class RuleRESTController {

    private final RuleService ruleService;

    @Autowired
    public RuleRESTController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping
    public JSONResponse getAll(@PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ruleService.getByPageable(pageable);
    }

    @GetMapping("{id}")
    public Rule getById(@PathVariable String id) {
        return ruleService.getById(Parser.toLong(id).orElseThrow(BadRequestException::new));
    }

    @PostMapping
    public void add(@Valid @RequestBody Rule rule) {
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

