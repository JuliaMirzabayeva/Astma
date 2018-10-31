package com.example.jjp.astma.modules.top.panel

import android.os.Bundle
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.models.QuotesManager
import com.example.jjp.astma.modules.main.MainActivity
import nucleus.presenter.Presenter
import javax.inject.Inject

class TopPanelFragmentPresenter : Presenter<TopPanelFragment>() {
    @Inject
    lateinit var quotesManager: QuotesManager

    private val dateChangeListener = object : MainActivity.DateChangeListener {
        override fun onDateChanged(day: Int, month: Int, year: Int) {}

        override fun onDateChanged(month: Int, year: Int) {
            view?.setPickerText(year, month)
        }
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
    }

    override fun onTakeView(view: TopPanelFragment?) {
        super.onTakeView(view)
        view?.setDefaultPickerDate()
        view?.setDefaultQuotesRange()
        (view?.activity as MainActivity).addDateListener(dateChangeListener)
    }

    override fun dropView() {
        (view?.activity as MainActivity).removeDateListener(dateChangeListener)
        super.dropView()
    }

    fun changeQuotesRange(maxRange: Int) {
        quotesManager.changeQuotesRange(maxRange)
    }
}