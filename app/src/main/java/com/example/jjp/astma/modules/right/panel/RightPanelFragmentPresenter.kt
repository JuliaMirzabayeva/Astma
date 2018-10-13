package com.example.jjp.astma.modules.right.panel

import com.example.jjp.astma.modules.main.MainActivity
import nucleus.presenter.Presenter
import java.util.*

class RightPanelFragmentPresenter : Presenter<RightPanelFragment>() {

    private val dateChangeListener = object : MainActivity.DateChangeListener {
        override fun onDateChanged(day: Int, month: Int, year: Int) {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, day)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.YEAR, year)
            view?.setDate(calendar.time)
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