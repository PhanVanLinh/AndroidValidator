package com.linh.androidvalidator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.linh.androidvalidator.library.EditTextValidator
import com.linh.androidvalidator.library.rule.EmailRule
import com.linh.androidvalidator.library.rule.EmptyRule
import kotlinx.android.synthetic.main.activity_main.*

class EditTextActivity : AppCompatActivity() {

	private lateinit var usernameValidator: EditTextValidator
	private lateinit var passwordValidator: EditTextValidator

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		usernameValidator = EditTextValidator(edt_username)
		passwordValidator = EditTextValidator(edt_password)

		usernameValidator
				.addRule(EmailRule("must be email"))
				.addRule(EmptyRule("not empty"))
				.setOnErrorListener {
					text_username_error.text = it[0]
				}
				.setOnValidListener {
					text_username_error.text = ""
				}
				.setOnValidStateChange { oldState, newState ->
					enableButton()
				}

		passwordValidator.addRule(EmptyRule("not empty"))
				.setOnErrorListener {
					text_password_error.text = it[0]
				}
				.setOnValidListener {
					text_password_error.text = ""
				}
				.setOnValidStateChange { oldState, newState ->
					enableButton()
				}

		findViewById<Button>(R.id.button_verity).setOnClickListener {
			handleButtonClick()
		}
	}

	private fun handleButtonClick() {
		if (isValidAll()) {
			Toast.makeText(this, "succeess", Toast.LENGTH_SHORT).show()
		} else {
			usernameValidator.valid()
			passwordValidator.valid()
		}
	}

	private fun enableButton() {
		button_verity.isEnabled = isValidAll()
	}

	private fun isValidAll(): Boolean {
		return usernameValidator.isValid && passwordValidator.isValid
	}
}
