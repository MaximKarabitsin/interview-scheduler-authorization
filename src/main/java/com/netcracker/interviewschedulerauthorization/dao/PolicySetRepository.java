package com.netcracker.interviewschedulerauthorization.dao;

import com.netcracker.interviewschedulerauthorization.entities.PolicySet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicySetRepository extends JpaRepository<PolicySet, Long> {
}

