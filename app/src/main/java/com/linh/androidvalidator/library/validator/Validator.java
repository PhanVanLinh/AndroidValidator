package com.linh.androidvalidator.library.validator;

import com.linh.androidvalidator.library.rule.Rule;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private List<Rule> rules = new ArrayList<>();

    public Validator addRule(Rule rule) {
        rules.add(rule);
        return this;
    }

    public List<Rule> valid(String target) {
        List<Rule> errors = new ArrayList<>();
        for (Rule rule : rules) {
            if (!rule.valid(target)) {
                errors.add(rule);
            }
        }
        return errors;
    }
}
