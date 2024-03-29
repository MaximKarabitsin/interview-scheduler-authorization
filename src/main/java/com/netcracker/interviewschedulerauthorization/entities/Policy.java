package com.netcracker.interviewschedulerauthorization.entities;

import com.netcracker.interviewschedulerauthorization.intities.enums.CombineAlgorithm;
import com.netcracker.interviewschedulerauthorization.utils.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "policies")
public class Policy implements Targeter {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Description can't be empty")
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank(message = "Target can't be empty")
    @Column(name = "target", nullable = false)
    private String target;


    @NotNull(message = "Combine algorithm can't be empty")
    @Enumerated(EnumType.STRING)
    @Column(name = "combine_algorithm", nullable = false)
    private CombineAlgorithm algorithm;

    @NotEmpty(message = "Rules can't be empty")
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "policies_rules",
            joinColumns = @JoinColumn(name = "policy_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<Rule> rules;

    public Policy() {
    }

    public Policy update(Policy policy) {
        if (!Validator.isEmpty(policy.name)) name = policy.name;
        if (!Validator.isEmpty(policy.description)) description = policy.description;
        if (!Validator.isEmpty(policy.target)) target = policy.target;
        if (policy.algorithm != null) algorithm = policy.algorithm;
        if (policy.rules != null && !policy.rules.isEmpty()) rules = policy.rules;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public CombineAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(CombineAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }
}
