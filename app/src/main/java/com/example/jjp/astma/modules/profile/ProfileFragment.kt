package com.example.jjp.astma.modules.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusFragment

@RequiresPresenter(ProfileFragmentPresenter :: class)
class ProfileFragment : NucleusFragment<ProfileFragmentPresenter>() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutButton.setOnClickListener {
            goToLoginActivity()
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(activity.baseContext, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}