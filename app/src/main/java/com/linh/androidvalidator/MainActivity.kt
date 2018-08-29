package com.linh.androidvalidator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import com.linh.androidvalidator.library.Validator
import com.linh.androidvalidator.library.rule.EmptyRule
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private var usernameValidator = Validator()
	private var passwordValidator = Validator()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		usernameValidator.addRule(EmptyRule("not empty"))
				.setOnErrorListener {
					text_username_error.text = it[0]
				}
				.setOnValidListener {
					text_username_error.text = ""
				}
		passwordValidator.addRule(EmptyRule("not empty"))
				.setOnErrorListener {
					text_password_error.text = it[0]
				}
				.setOnValidListener {
					text_password_error.text = ""
				}

		edt_username.addTextChangedListener(object : TextWatcher {
			override fun afterTextChanged(s: Editable?) {
				validateUserName()
				enableButton()
			}

			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
			}
		})

		edt_password.addTextChangedListener(object : TextWatcher {
			override fun afterTextChanged(s: Editable?) {
				validatePassword()
				enableButton()
			}

			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
			}
		})

		findViewById<Button>(R.id.button_verity).setOnClickListener {
			handleButtonClick()
		}
	}

	fun validateUserName() {
		usernameValidator.valid(edt_username.text.toString())
	}

	fun validatePassword() {
		passwordValidator.valid(edt_password.text.toString())
	}

	private fun handleButtonClick() {
		if (isValidAll()) {
			Toast.makeText(this, "succeess", Toast.LENGTH_SHORT).show()
		} else {
			validateUserName()
			validatePassword()
		}
	}

	private fun enableButton() {
		button_verity.isEnabled = isValidAll()
	}

	private fun isValidAll(): Boolean {
		return usernameValidator.isValid && passwordValidator.isValid
	}
}
