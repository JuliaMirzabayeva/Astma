package com.example.jjp.astma.modules.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusFragment

@RequiresPresenter(ProfileFragmentPresenter :: class)
class ProfileFragment : NucleusFragment<ProfileFragmentPresenter>() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_profile, container, false)
    }
}