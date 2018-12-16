package com.example.jjp.astma.modules.top.panel

import android.os.Bundle
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.models.QuotesManager
import com.example.jjp.astma.modules.main.MainActivity
import nucleus.presenter.Presenter
import java.util.*
import javax.inject.Inject

class TopPanelFragmentPresenter : Presenter<TopPanelFragment>() {
    @Inject lateinit var quotesManager: QuotesManager

    private val dateChangeListener = object : MainActivity.DateChangeListener {
        override fun onDateChanged(day: Int, month: Int, year: Int) {}

        override fun onDateChanged(month: Int, year: Int) {
            view?.setPickerText(year, month)
            view?.changeQuotesRange(year, month)
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

    fun changeQuotesRange(month: Int, maxRange: Int) {
        quotesManager.changeQuotesRange(month, maxRange)
    }

    fun getCalendar(year: Int, month: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, DEFAULT_DAY)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        return calendar
    }

    companion object {
        const val DEFAULT_DAY = 1
    }
}