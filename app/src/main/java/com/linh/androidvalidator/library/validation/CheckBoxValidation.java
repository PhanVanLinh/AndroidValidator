package com.linh.androidvalidator.library.validation;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.linh.androidvalidator.library.validator.Validator;

public class CheckBoxValidation extends ViewValidation {

    public CheckBoxValidation(final CheckBox view, Validator validator) {
        super(view, validator);
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onViewDataChangeListener.onViewDataChange(view);
            }
        });
    }

    @Override
    String getValidationTarget() {
        return String.valueOf(((CheckBox) view).isChecked());
    }
}
