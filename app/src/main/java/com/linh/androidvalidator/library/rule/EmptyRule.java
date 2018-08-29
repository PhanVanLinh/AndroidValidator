package com.linh.androidvalidator.library.rule;

import android.text.TextUtils;

public class EmptyRule extends Rule{

    public EmptyRule(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean valid(String content) {
        return !TextUtils.isEmpty(content);
    }
}
