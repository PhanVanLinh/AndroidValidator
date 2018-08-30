package com.linh.androidvalidator.library.rule;

public class CheckedRule extends Rule {

    public CheckedRule(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean valid(String target) {
        return target.equals(String.valueOf(true));
    }
}
