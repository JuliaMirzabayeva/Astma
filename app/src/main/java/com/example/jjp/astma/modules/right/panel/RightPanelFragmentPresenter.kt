package com.example.jjp.astma.modules.right.panel

import android.os.Bundle
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.models.QuotesManager
import com.example.jjp.astma.modules.main.MainActivity
import nucleus.presenter.Presenter
import java.util.*
import javax.inject.Inject

class RightPanelFragmentPresenter : Presenter<RightPanelFragment>() {
    @Inject lateinit var quotesManager : QuotesManager

    private val dateChangeListener = object : MainActivity.DateChangeListener {
        override fun onDateChanged(day: Int, month: Int, year: Int) {
            val calendar = getCalendar(day, year, month)
            view?.setDate(calendar.time)
        }

        override fun onDateChanged(month: Int, year: Int) {

        }
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
    }

    override fun onTakeView(view: RightPanelFragment?) {
        super.onTakeView(view)
        (view?.activity as MainActivity).addDateListener(dateChangeListener)
    }

    override fun dropView() {
        (view?.activity as MainActivity).removeDateListener(dateChangeListener)
        super.dropView()
    }

    fun addQuote(quote: Quote){
        quotesManager.addQuote(quote)
    }

    private fun getCalendar(day : Int, year: Int, month: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        return calendar
    }
}