package com.linh.androidvalidator.library.validation;

import android.view.View;

import com.linh.androidvalidator.library.OnViewDataChangeListener;
import com.linh.androidvalidator.library.rule.Rule;
import com.linh.androidvalidator.library.validator.Validator;

import java.util.List;

public abstract class ViewValidation {
    protected View view;
    private Validator validator;
    OnViewDataChangeListener onViewDataChangeListener;

    ViewValidation(View view, Validator validator) {
        this.view = view;
        this.validator = validator;
    }

    public void setOnViewDataChangeListener(OnViewDataChangeListener onViewDataChangeListener) {
        this.onViewDataChangeListener = onViewDataChangeListener;
    }

    public View getView() {
        return view;
    }

    public ValidationError valid() {
        List<Rule> errorRule = validator.valid(getValidationTarget());
        if (errorRule.isEmpty()) {
            return null;
        }
        return new ValidationError(view, errorRule);
    }

    abstract String getValidationTarget();
}
