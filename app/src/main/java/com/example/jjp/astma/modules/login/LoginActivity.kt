package com.example.jjp.astma.modules.login

import android.content.Intent
import android.os.Bundle
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.chart.ChartActivity
import kotlinx.android.synthetic.main.activity_login.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusActivity

@RequiresPresenter(LoginActivityPresenter::class)
class LoginActivity : NucleusActivity<LoginActivityPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUpButton.setOnClickListener { if (validateLoginFields()) goToChartActivity() }
    }

    private fun validateLoginFields(): Boolean {
        emailLayout.error = if (email.text.isEmpty()) getString(R.string.error_field_required) else null
        deviceCodeLayout.error = when {
            deviceCode.text.isEmpty() -> getString(R.string.error_field_required)
            deviceCode.text.length != DEVICE_CODE_LENGTH -> getString(R.string.error_invalid_device_code)
            else -> null
        }
        return emailLayout.error == null && deviceCode.error == null
    }

    private fun goToChartActivity() {
        val intent = Intent(this.baseContext, ChartActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    companion object {
        const val DEVICE_CODE_LENGTH = 10
    }
}

