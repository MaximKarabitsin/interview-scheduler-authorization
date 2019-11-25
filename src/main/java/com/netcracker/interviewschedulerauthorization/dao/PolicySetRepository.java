package com.netcracker.interviewschedulerauthorization.dao;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicySetRepository extends JpaRepository<PolicySet, Long> {
}

