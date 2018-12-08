package com.example.jjp.astma.modules.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
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

        // signUpButton.setOnClickListener { if (validateLoginFields())  }

        signInButton.setOnClickListener {
            presenter.signInUser(email.text.toString(), password.text.toString())
        }
    }

//    private fun validateLoginFields(): Boolean {
//        emailLayout.error = if (email.text.isEmpty()) getString(R.string.error_field_required) else null
//        passwordLayout.error = when {
//            password.text.isEmpty() -> getString(R.string.error_field_required)
//            password.text.length != DEVICE_CODE_LENGTH -> getString(R.string.error_invalid_device_code)
//            else -> null
//        }
//        return emailLayout.error == null && password.error == null
//    }

    fun goToChartActivity() {
        val intent = Intent(this.baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun showError(error: String? = null) {
        Snackbar.make(
                rootLayout,
                error ?: getString(R.string.network_error),
                Snackbar.LENGTH_SHORT
        ).show()
    }

    companion object {
        //const val DEVICE_CODE_LENGTH = 10
    }
}

