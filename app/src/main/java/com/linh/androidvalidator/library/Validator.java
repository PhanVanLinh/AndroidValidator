package com.linh.androidvalidator.library;

import com.linh.androidvalidator.library.rule.Rule;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private OnValid onValidListener;
    private OnError onErrorListener;
    private OnValidStateChange onValidStateChange;
    private List<Rule> rules = new ArrayList<>();
    private boolean isValid;

    public Validator addRule(Rule rule) {
        rules.add(rule);
        return this;
    }

    public Validator setOnValidListener(OnValid onValidListener) {
        this.onValidListener = onValidListener;
        return this;
    }

    public Validator setOnErrorListener(OnError onErrorListener) {
        this.onErrorListener = onErrorListener;
        return this;
    }

    public void setOnValidStateChange(OnValidStateChange onValidStateChange) {
        this.onValidStateChange = onValidStateChange;
    }

    public void valid(String target) {
        boolean state = isValid;
        List<String> errors = new ArrayList<>();
        for (Rule rule : rules) {
            if (!rule.valid(target)) {
                errors.add(rule.getError());
            }
        }
        if (errors.isEmpty()) {
            isValid = true;
            notifyValid();
        } else {
            isValid = false;
            notifyError(errors);
        }
        if (state != isValid) {
            notifyValidStateChange(state, isValid);
        }
    }

    public boolean isValid() {
        return isValid;
    }

    private void notifyValid() {
        if (onValidListener != null) onValidListener.onValid();
    }

    private void notifyError(List<String> errors) {
        if (onErrorListener != null) onErrorListener.onError(errors);
    }

    private void notifyValidStateChange(boolean oldState, boolean newState) {
        if (onValidStateChange != null) onValidStateChange.onValidStateChange(oldState, newState);
    }

    interface OnError {
        void onError(List<String> errors);
    }

    interface OnValid {
        void onValid();
    }

    interface OnValidStateChange {
        void onValidStateChange(boolean oldState, boolean newState);
    }
}
