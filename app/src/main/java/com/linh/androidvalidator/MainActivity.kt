package com.linh.androidvalidator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.linh.androidvalidator.library.CompositeValidation
import com.linh.androidvalidator.library.rule.CheckedRule
import com.linh.androidvalidator.library.rule.EmptyRule
import com.linh.androidvalidator.library.validation.CheckBoxValidation
import com.linh.androidvalidator.library.validation.EditTextValidation
import com.linh.androidvalidator.library.validation.ValidationError
import com.linh.androidvalidator.library.validator.Validator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private var compositeValidation = CompositeValidation()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		compositeValidation.add(EditTextValidation(edt_username, Validator().addRule(EmptyRule("user name error"))))
		compositeValidation.add(EditTextValidation(edt_password, Validator().addRule(EmptyRule("password error"))))
		compositeValidation.add(CheckBoxValidation(cb_accept, Validator().addRule(CheckedRule("please check"))))

		compositeValidation.setListener(object : CompositeValidation.CompositeValidationListener {

			override fun onValidationSuccess(views: MutableList<View>) {
				for (v in views) {
					if (v.id == R.id.edt_username) {
						text_username_error.text = ""
					}
					if (v.id == R.id.edt_password) {
						text_password_error.text = ""
					}
					if (v.id == R.id.cb_accept) {
						text_cb_accept_error.text = ""
					}
				}
			}

			override fun onValidationError(errors: MutableList<ValidationError>) {
				button_verity.isEnabled = false
				for (error in errors) {
					if (error.view.id == R.id.edt_username) {
						text_username_error.text = error.errorRules[0].error
					}
					if (error.view.id == R.id.edt_password) {
						text_password_error.text = error.errorRules[0].error
					}
					if (error.view.id == R.id.cb_accept) {
						text_cb_accept_error.text = error.errorRules[0].error
					}
				}
			}

			override fun onAllValidationSuccess() {
				button_verity.isEnabled = true
			}
		})

		findViewById<Button>(R.id.button_verity).setOnClickListener {
			handleButtonClick()
		}
	}

	private fun handleButtonClick() {
		if (compositeValidation.isAllValidationSuccess) {
			Toast.makeText(this, "succeess", Toast.LENGTH_SHORT).show()
		} else {
			compositeValidation.validateAll()
		}
	}
}
