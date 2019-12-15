package com.netcracker.interviewschedulerauthorization.entities;

import com.netcracker.interviewschedulerauthorization.intities.enums.CombineAlgorithm;
import com.netcracker.interviewschedulerauthorization.utils.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "policy_sets")
public class PolicySet implements Targeter{
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

    @NotEmpty(message = "Policies can't be empty")
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "policy_sets_policies",
            joinColumns = @JoinColumn(name = "policy_set_id"),
            inverseJoinColumns = @JoinColumn(name = "policy_id"))
    private Set<Policy> policies;

    public PolicySet() {
    }

    public PolicySet update(PolicySet policySet) {
        if (!Validator.isEmpty(policySet.name)) name = policySet.name;
        if (!Validator.isEmpty(policySet.description)) description = policySet.description;
        if (!Validator.isEmpty(policySet.target)) target = policySet.target;
        if (policySet.algorithm != null) algorithm = policySet.algorithm;
        if (policySet.policies != null && !policySet.policies.isEmpty()) policies = policySet.policies;
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

    public Set<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(Set<Policy> policies) {
        this.policies = policies;
    }
}
