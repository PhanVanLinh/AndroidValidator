package com.linh.androidvalidator.library;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditTextValidator extends Validator{
    private EditText editText;

    public EditTextValidator(EditText editText){
        this.editText = editText;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                valid(s.toString());
            }
        });
    }

    public void valid(){
        valid(editText.getText().toString());
    }
}
