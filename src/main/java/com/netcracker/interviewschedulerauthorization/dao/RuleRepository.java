package com.netcracker.interviewschedulerauthorization.dao;

import com.netcracker.interviewschedulerauthorization.entities.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {

}
