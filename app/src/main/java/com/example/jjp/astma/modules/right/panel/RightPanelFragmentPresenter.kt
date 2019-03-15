package com.example.jjp.astma.modules.right.panel

import android.os.Bundle
import com.example.jjp.astma.api.request.EditQuoteRequest
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.models.QuotesManager
import com.example.jjp.astma.models.quotes.QuoteConverter
import com.example.jjp.astma.models.quotes.QuotesRepository
import com.example.jjp.astma.modules.main.MainActivity
import com.example.jjp.astma.preferences.CommonPreferencesHelper
import nucleus.presenter.Presenter
import java.util.*
import javax.inject.Inject

class RightPanelFragmentPresenter : Presenter<RightPanelFragment>() {
    @Inject lateinit var quotesManager: QuotesManager
    @Inject lateinit var quotesRepository: QuotesRepository
    @Inject lateinit var commonPreferencesHelper: CommonPreferencesHelper

    private var rightPanelUseCase: RightPanelUseCase? = null

    private val quoteConverter: QuoteConverter = QuoteConverter()

    private var editQuote: Quote? = null

    private val dateChangeListener = object : MainActivity.DateChangeListener {
        override fun onDateChanged(day: Int, month: Int, year: Int) {
            val calendar = getCalendar(day, month, year)
            view?.setDate(calendar.time)
        }

        override fun onDateChanged(month: Int, year: Int) {

        }
    }

    private val onAddResult: (quote: Quote) -> Unit = {
        view?.clearQuoteValue()
        quotesManager.addQuote(it)
    }

    private val onEditResult: (quote: Quote) -> Unit = {
        view?.clearQuoteValue()
        editQuote = null
        quotesManager.editQuote(it)
    }

    private val onError: (error: String?) -> Unit = { it ->
        view?.showError(it)
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
        if (quotesManager.isNewQuote(quote)) {
            commonPreferencesHelper.userToken?.let { token ->
                val quoteRequest = quoteConverter.convertQuoteToQuoteRequest(quote, token)
                rightPanelUseCase?.addQuote(quoteRequest, onAddResult, onError)
            }
        } else {
            editQuote = Quote(quotesManager.getQuoteIdByDate(quote)!!,
                    quote.value,
                    quote.date,
                    quote.isMorning)
            showEditQuoteDialog()
        }
    }

    private fun showEditQuoteDialog() {
        view?.showEditQuoteAlertDialog(::editQuote)
    }

    private fun editQuote() {
        editQuote?.let { quote ->
            commonPreferencesHelper.userToken?.let { token ->
                rightPanelUseCase?.editQuote(EditQuoteRequest(quote.id, quote.value, token),
                        onEditResult,
                        onError)
            }
        }
    }

    private fun getCalendar(day: Int, month: Int, year: Int): GregorianCalendar {
        return GregorianCalendar(year, month, day)
    }
}