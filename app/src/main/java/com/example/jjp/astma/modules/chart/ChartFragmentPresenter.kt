package com.example.jjp.astma.modules.chart

import android.os.Bundle
import com.example.jjp.astma.R
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.models.QuotesManager
import com.example.jjp.astma.models.quotes.QuoteConverter
import com.example.jjp.astma.models.quotes.QuotesRepository
import com.example.jjp.astma.preferences.CommonPreferencesHelper
import com.example.jjp.astma.preferences.UserPreferencesHelper
import nucleus.presenter.Presenter
import javax.inject.Inject

class ChartFragmentPresenter : Presenter<ChartFragment>() {
    @Inject lateinit var quotesManager: QuotesManager
    @Inject lateinit var quotesRepository: QuotesRepository
    @Inject lateinit var commonPreferencesHelper: CommonPreferencesHelper
    @Inject lateinit var userPreferencesHelper: UserPreferencesHelper

    private var chartUseCase: ChartUseCase? = null

    private val quotesConverter = QuoteConverter()

    private val quotesListener = object : QuotesManager.QuoteListener {
        override fun onQuoteAdded(quote: Quote) {
            fillChart(quotesManager.getCurrentQuotes())
        }

        override fun onQuoteEdited(quote: Quote) {
            fillChart(quotesManager.getCurrentQuotes())
        }

        override fun onQuotesRangeChanged(month: Int, year: Int, maxRange: Int) {
            view?.setXRange(maxRange)
            loadQuotes(month, year, maxRange)
        }
    }

    private val onResult: (quotes: List<Quote>) -> Unit = { list ->
        quotesManager.setQuotes(list)
        quotesManager.getCurrentQuotes()?.let {
            initChart(it)
        }
    }

    private val onError: (error: String?) -> Unit = { it ->
        view?.showError(it)
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
        chartUseCase = ChartUseCase(quotesRepository)
    }

    override fun onTakeView(view: ChartFragment) {
        super.onTakeView(view)
        quotesManager.addQuoteListener(quotesListener)
    }

    private fun loadQuotes(month: Int, year: Int, maxRange: Int) {
        commonPreferencesHelper.userToken?.let { token ->
            val quotesRequest = quotesConverter.convertDataToQuotesRequest(month, year, maxRange, token)
            chartUseCase?.loadQuotes(quotesRequest, onResult, onError)
        }
    }

    override fun dropView() {
        quotesManager.removeQuoteListener(quotesListener)
        super.dropView()
    }

    fun fillChart(quotes: List<Quote>?) {
        view?.clearChart()
        quotes?.let {
            view?.addQuotes(it)
        }
    }

    fun initChart(quotes: List<Quote>) {
        view?.initChart(quotes)
        addLimitLines()
    }

    private fun addLimitLines() {
        view?.addLimitLine(userPreferencesHelper.topBound, R.color.colorChartRedLine)
        view?.addLimitLine(userPreferencesHelper.userRate, R.color.colorChartYellowLine)
        view?.addLimitLine(userPreferencesHelper.bottomBound, R.color.colorChartGreenLine)
    }

    fun getDay(quote: Quote) : Int{
        return quotesManager.getDay(quote)
    }
}