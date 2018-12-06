package com.example.jjp.astma.modules.right.panel

import android.os.Bundle
import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.models.QuotesManager
import com.example.jjp.astma.models.quotes.QuotesRepository
import com.example.jjp.astma.modules.main.MainActivity
import nucleus.presenter.Presenter
import java.util.*
import javax.inject.Inject

class RightPanelFragmentPresenter : Presenter<RightPanelFragment>() {
    @Inject
    lateinit var quotesManager: QuotesManager
    @Inject
    lateinit var quotesRepository: QuotesRepository

    private var rightPanelUseCase: RightPanelUseCase? = null

    private val dateChangeListener = object : MainActivity.DateChangeListener {
        override fun onDateChanged(day: Int, month: Int, year: Int) {
            val calendar = getCalendar(day, year, month)
            view?.setDate(calendar.time)
        }

        override fun onDateChanged(month: Int, year: Int) {

        }
    }

    private val onResult: (quote: Quote) -> Unit = {
        quotesManager.addQuote(it)
    }

    private val onFailure: (throwable: Throwable?) -> Unit = {
        view?.showNetworkError()
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
        rightPanelUseCase = RightPanelUseCase(quotesRepository)
    }

    override fun onTakeView(view: RightPanelFragment?) {
        super.onTakeView(view)
        (view?.activity as MainActivity).addDateListener(dateChangeListener)
    }

    override fun dropView() {
        (view?.activity as MainActivity).removeDateListener(dateChangeListener)
        super.dropView()
    }

    fun addQuote(quote: Quote) {
        val quoteDate = getCalendar(quote.date.day, quote.date.month, quote.date.year).time
        addQuote(quote.value, quoteDate, quote.isMorning)
    }

    private fun addQuote(value: Int, date: Date, isMorning: Boolean) {
        rightPanelUseCase?.addQuote(QuoteRequest(value, date, isMorning, "5afc7701-a637-436e-953a-7744e77095df"), onResult, onFailure)
    }

    private fun getCalendar(day: Int, year: Int, month: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        return calendar
    }
}