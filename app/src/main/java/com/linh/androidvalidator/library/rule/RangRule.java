package com.linh.androidvalidator.library.rule;

import org.jetbrains.annotations.Nullable;

public class RangRule extends Rule {
    private Integer min;
    private Integer max;

    public RangRule(String errorMessage, @Nullable Integer min, @Nullable Integer max) {
        super(errorMessage);
    }

    @Override
    public boolean valid(String content) {
        int len = content.length();
        if (min != null && len <= min) {
            return false;
        }
        if (max != null && len > max) {
            return false;
        }
        return true;
    }
}
