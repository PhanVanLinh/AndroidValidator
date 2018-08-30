package com.linh.androidvalidator.library.rule;

public abstract class Rule {
    private String error;

    public Rule(String errorMessage) {
        this.error = errorMessage;
    }

    public abstract boolean valid(String target);

    public String getError() {
        return error;
    }
}
