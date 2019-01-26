package com.example.jjp.astma.modules.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
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

    private fun showFinishRegistrationView(){
        val registrationView = layoutInflater.inflate(R.layout.view_registration, loginRootLayout, false)
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

    fun hideProgress(){
        progress.visibility = View.GONE
    }
}

