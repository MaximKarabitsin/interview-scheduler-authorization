package com.netcracker.interviewschedulerauthorization.dao;

import com.netcracker.interviewschedulerauthorization.entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
