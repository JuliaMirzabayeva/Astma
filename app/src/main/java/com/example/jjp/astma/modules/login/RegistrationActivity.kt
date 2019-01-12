package com.example.jjp.astma.modules.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.main.MainActivity
import kotlinx.android.synthetic.main.activity_registration.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusActivity

@RequiresPresenter(RegistrationActivityPresenter::class)
class RegistrationActivity : NucleusActivity<RegistrationActivityPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        signUpButton.setOnClickListener {
            //progress.visibility = View.VISIBLE
            //presenter.signInUser(email.text.toString(), password.text.toString())
        }

        signInButton.setOnClickListener {
           goToLoginActivity()
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(this.baseContext, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

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

    fun hideProgress(){
        progress.visibility = View.GONE
    }
}