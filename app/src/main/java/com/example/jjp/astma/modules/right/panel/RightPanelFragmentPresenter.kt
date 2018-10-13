package com.example.jjp.astma.modules.right.panel

import com.example.jjp.astma.modules.main.MainActivity
import nucleus.presenter.Presenter

class RightPanelFragmentPresenter : Presenter<RightPanelFragment>() {

    private val dateChangeListener = object : MainActivity.DateChangeListener {
        override fun onDateChanged(day: Int, month: Int, year: Int) {
            view?.setDate(day, month, year)
        }
    }

    override fun onTakeView(view: RightPanelFragment?) {
        super.onTakeView(view)
        (view?.activity as MainActivity).addDateListener(dateChangeListener)
    }

    override fun dropView() {
        (view?.activity as MainActivity).removeDateListener(dateChangeListener)
        super.dropView()
    }
}