package com.linh.androidvalidator.library.rule;

import android.util.Patterns;

public class EmailRule extends Rule{

    public EmailRule(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean valid(String target) {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
