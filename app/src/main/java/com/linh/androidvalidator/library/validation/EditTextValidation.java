package com.linh.androidvalidator.library.validation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.linh.androidvalidator.library.validator.Validator;

public class EditTextValidation extends ViewValidation {

    public EditTextValidation(EditText editText, Validator validator) {
        super(editText, validator);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onViewDataChangeListener.onViewDataChange(view);
            }
        });
    }

    @Override
    String getValidationTarget() {
        return String.valueOf(((EditText) view).getText());
    }
}
