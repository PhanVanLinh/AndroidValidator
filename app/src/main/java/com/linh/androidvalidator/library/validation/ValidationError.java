package com.linh.androidvalidator.library.validation;

import android.view.View;

import com.linh.androidvalidator.library.rule.Rule;

import java.util.List;

public class ValidationError {
    private final View view;
    private final List<Rule> errorRules;

    ValidationError(final View view, final List<Rule> errorRules) {
        this.view = view;
        this.errorRules = errorRules;
    }

    public View getView() {
        return view;
    }

    public List<Rule> getErrorRules() {
        return errorRules;
    }
}