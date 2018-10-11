package com.example.jjp.astma.modules.main

import android.os.Bundle
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.right.panel.RightPanelFragment
import kotlinx.android.synthetic.main.activity_main.view.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusActivity


@RequiresPresenter(MainActivityPresenter::class)
class MainActivity : NucleusActivity<MainActivityPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun inflateRightPanel(){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.rightPanelContainer, RightPanelFragment())
        fragmentTransaction.commit()
    }

}