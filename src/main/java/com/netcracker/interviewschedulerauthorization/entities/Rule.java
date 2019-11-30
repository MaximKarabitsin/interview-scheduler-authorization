package com.netcracker.interviewschedulerauthorization.entities;

import com.netcracker.interviewschedulerauthorization.utils.Validator;

import javax.persistence.*;

@Entity
@Table(name = "rules")
public class Rule {

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

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "effect", nullable = false)
    private Boolean effect;

    public Rule() {
    }

    public Rule(Long id) {
        this.id = id;
    }

    public Rule update(Rule rule) {
        if (!Validator.isEmpty(rule.name)) name = rule.name;
        if (!Validator.isEmpty(rule.description)) description = rule.description;
        if (!Validator.isEmpty(rule.target)) target = rule.target;
        if (!Validator.isEmpty(rule.condition)) condition = rule.condition;
        if (rule.effect != null) effect = rule.effect;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean getEffect() {
        return effect;
    }

    public void setEffect(Boolean effect) {
        this.effect = effect;
    }
}