package com.example.jjp.astma.modules.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusActivity

@RequiresPresenter(LoginActivityPresenter::class)
class LoginActivity : NucleusActivity<LoginActivityPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton.setOnClickListener {
            if (validateLoginFields()) {
                progress.visibility = View.VISIBLE
                presenter.signInUser(email.text.toString(), password.text.toString())
            }
        }

        signUpButton.setOnClickListener {
            if (validateLoginFields()) {
                showFinishRegistrationView()
            }
        }
    }

    private fun validateLoginFields(): Boolean {
        emailLayout.error = if (email.text.isEmpty()) getString(R.string.error_field_required) else null
        passwordLayout.error = if (password.text.isEmpty()) getString(R.string.error_field_required) else null
        return emailLayout.error == null && passwordLayout.error == null
    }

    private fun showFinishRegistrationView() {
        val registrationView = layoutInflater.inflate(R.layout.view_registration, loginRootLayout, false)

        val finishSignUpButton = registrationView.findViewById<Button>(R.id.finishSignUpButton)
        val backButton = registrationView.findViewById<ImageView>(R.id.registrationBackImage)
        val nameLayout = registrationView.findViewById<TextInputLayout>(R.id.nameLayout)
        val name = registrationView.findViewById<TextView>(R.id.name)
        val surnameLayout = registrationView.findViewById<TextInputLayout>(R.id.surnameLayout)
        val surname = registrationView.findViewById<TextView>(R.id.surname)
        val birthLayout = registrationView.findViewById<TextInputLayout>(R.id.birthLayout)
        val birth = registrationView.findViewById<TextView>(R.id.birth)
        val heightLayout = registrationView.findViewById<TextInputLayout>(R.id.heightLayout)
        val height = registrationView.findViewById<TextView>(R.id.height)
        val weightLayout = registrationView.findViewById<TextInputLayout>(R.id.weightLayout)
        val weight = registrationView.findViewById<TextView>(R.id.weight)

        fun validateRegistrationFields() : Boolean {
            nameLayout.error = if (name.text.isEmpty()) getString(R.string.error_field_required) else null
            surnameLayout.error = if (surname.text.isEmpty()) getString(R.string.error_field_required) else null
            birthLayout.error = if (birth.text.isEmpty()) getString(R.string.error_field_required) else null
            heightLayout.error = if (height.text.isEmpty()) getString(R.string.error_field_required) else null
            weightLayout.error = if (weight.text.isEmpty()) getString(R.string.error_field_required) else null

            return nameLayout.error == null &&
                    surnameLayout.error == null &&
                    birthLayout.error == null &&
                    heightLayout.error == null &&
                    weightLayout.error == null
        }

        finishSignUpButton.setOnClickListener {
            if (validateRegistrationFields()) {
            }
        }

        backButton.setOnClickListener {
            loginRootLayout.removeView(registrationView)
        }

        loginRootLayout.addView(registrationView)
    }

    fun goToChartActivity() {
        val intent = Intent(this.baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun showError(error: String? = null) {
        Snackbar.make(
                loginRootLayout,
                error ?: getString(R.string.network_error),
                Snackbar.LENGTH_SHORT
        ).show()
    }

    fun hideProgress() {
        progress.visibility = View.GONE
    }
}

