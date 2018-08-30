package com.linh.androidvalidator.library;

import android.annotation.SuppressLint;
import android.view.View;

import com.linh.androidvalidator.library.validation.ValidationError;
import com.linh.androidvalidator.library.validation.ViewValidation;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressLint("UseSparseArrays")
public class CompositeValidation implements OnViewDataChangeListener {
    private HashMap<Integer, ViewValidation> validations = new HashMap<>();
    private Set<View> validationSuccessViews = new HashSet<>();
    private CompositeValidationListener listener;

    public void add(ViewValidation validation) {
        validations.put(validation.getView().getId(), validation);
        validation.setOnViewDataChangeListener(this);
    }

    /**
     * Validate all field with its rules and notify newest success and error
     */
    public void validateAll() {
        List<ValidationError> newErrors = new ArrayList<>();
        List<View> newSuccess = new ArrayList<>();
        for (ViewValidation validation : validations.values()) {
            ValidationError error = validation.valid();
            if (error == null) {
                newSuccess.add(validation.getView());
                validationSuccessViews.add(validation.getView());
            } else {
                newErrors.add(error);
                validationSuccessViews.remove(validation.getView());
            }
        }
        notifyValidationChanged(newSuccess, newErrors);
    }

    public boolean isAllValidationSuccess() {
        return validationSuccessViews.size() == validations.size();
    }

    public void setListener(CompositeValidationListener listener) {
        this.listener = listener;
    }

    /**
     * Validate the current changed field and return only success and error for this field
     */
    @Override
    public void onViewDataChange(final View view) {
        final ValidationError error = getValidation(view).valid();
        if (error == null) {
            validationSuccessViews.add(view);
            notifyValidationChanged(new ArrayList<View>() {{
                add(view);
            }}, null);
        } else {
            validationSuccessViews.remove(view);
            notifyValidationChanged(null, new ArrayList<ValidationError>() {{
                add(error);
            }});
        }
    }

    private void notifyValidationChanged(@Nullable List<View> newSuccess, @Nullable List<ValidationError> newErrors) {
        if (newErrors != null) {
            listener.onValidationError(newErrors);
        }
        if (newSuccess != null) {
            listener.onValidationSuccess(newSuccess);
        }
        if (isAllValidationSuccess()) {
            listener.onAllValidationSuccess();
        }
    }

    /**
     * Return validation by viewId
     */
    private ViewValidation getValidation(View view) {
        return validations.get(view.getId());
    }

    public interface CompositeValidationListener {
        /**
         * @param views validation success views
         */
        void onValidationSuccess(List<View> views);

        /**
         *
         */
        void onValidationError(List<ValidationError> errors);


        void onAllValidationSuccess();
    }
}
