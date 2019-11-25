package com.netcracker.interviewschedulerauthorization.entities;

import com.netcracker.interviewschedulerauthorization.intities.enums.CombineAlgorithm;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "policy_set")
public class PolicySet {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "target", nullable = false)
    private String target;

    @Enumerated(EnumType.STRING)
    @Column(name = "combine_algorithm", nullable = false)
    private CombineAlgorithm algorithm;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "policy_set_policy",
            joinColumns = @JoinColumn(name = "policy_set_id"),
            inverseJoinColumns = @JoinColumn(name = "policy_id"))
    private Set<Policy> policies;

    public PolicySet() {
    }

    public PolicySet update(PolicySet policySet) {
        if (policySet.name != null) name = policySet.name;
        if (policySet.description != null) description = policySet.description;
        if (policySet.target != null) target = policySet.target;
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
