package com.example.jjp.astma.modules.main

import nucleus.presenter.Presenter

class MainActivityPresenter : Presenter<MainActivity>() {

    override fun onTakeView(view: MainActivity?) {
        super.onTakeView(view)
        view?.inflateRightPanel()
    }
}