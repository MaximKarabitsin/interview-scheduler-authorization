package com.netcracker.interviewschedulerauthorization.services;

import com.netcracker.interviewschedulerauthorization.entities.Policy;

import java.util.List;

public interface PolicyService {
    List<Policy> getAll();

    Policy getById(String id);

    void add(Policy policy);

    void updateById(String id, Policy policy);

    void deleteById(String id);
}
