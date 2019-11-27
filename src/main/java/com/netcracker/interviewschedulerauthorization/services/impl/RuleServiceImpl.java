package com.netcracker.interviewschedulerauthorization.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netcracker.interviewschedulerauthorization.dao.RuleRepository;
import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.Rule;
import com.netcracker.interviewschedulerauthorization.exceptions.BadRequestException;
import com.netcracker.interviewschedulerauthorization.exceptions.NotFoundException;
import com.netcracker.interviewschedulerauthorization.model.JSONResponse;
import com.netcracker.interviewschedulerauthorization.services.RuleService;
import com.netcracker.interviewschedulerauthorization.utils.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public JSONResponse getAll() {
        Page<Rule> page = ruleRepository.findAll(Pageable.unpaged());
        return new JSONResponse(page.getTotalElements(), page.getContent());
    }

    @Override
    public JSONResponse getByPageAndSort(String page, String size, String sortBy, String sortDesc) {
        int pageNumber = Parser.toInteger(page).orElseThrow(BadRequestException::new);
        int pageSize = Parser.toInteger(size).orElseThrow(BadRequestException::new);
        if (--pageNumber<0) throw new BadRequestException();
        Sort sort;
        if(sortBy != null && !sortBy.isEmpty()) {
            boolean sortDescending = Parser.toBoolean(sortDesc).orElseThrow(BadRequestException::new);
            sort = Sort.by(sortBy);
            if (sortDescending) {
                sort = sort.descending();
            }
        } else {
            sort = Sort.unsorted();
        }
        Page<Rule> pageRules = ruleRepository.findAll(PageRequest.of(pageNumber,pageSize,sort));
        return new JSONResponse(pageRules.getTotalElements(),pageRules.getContent());
    }

    @Override
    public Set<Rule> getByPolicy(Policy policy) {
        return policy.getRules().stream()
                .map(Rule::getId)
                .distinct()
                .map(ruleRepository::findById)
                .map(x -> x.orElseThrow(BadRequestException::new))
                .collect(Collectors.toSet());
    }

    @Override
    public Rule getById(String id) {
        if (!id.matches("[0-9]+")) throw new NotFoundException();
        return ruleRepository.findById(Long.parseLong(id)).orElseThrow(NotFoundException::new);
    }

    @Override
    public void add(Rule rule) {
        rule.setId(null);
        ruleRepository.save(rule);
    }

    @Override
    public void updateById(String id, Rule rule) {
        ruleRepository.save(getById(id).update(rule));
    }

    @Override
    public void deleteById(String id) {
        ruleRepository.delete(getById(id));
    }
}
