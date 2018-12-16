package com.example.jjp.astma.modules.chart

import android.os.Bundle
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.models.QuotesManager
import com.example.jjp.astma.models.quotes.QuoteConverter
import com.example.jjp.astma.models.quotes.QuotesRepository
import com.example.jjp.astma.preferences.CommonPreferencesHelper
import nucleus.presenter.Presenter
import javax.inject.Inject

class ChartFragmentPresenter : Presenter<ChartFragment>() {
    @Inject lateinit var quotesManager: QuotesManager
    @Inject lateinit var quotesRepository: QuotesRepository

    private var chartUseCase: ChartUseCase? = null
    private var commonPreferencesHelper: CommonPreferencesHelper? = null

    private val quotesConverter = QuoteConverter()

    private val quotesListener = object : QuotesManager.QuoteListener {
        override fun onQuoteAdded(quote: Quote) {
            view?.addQuote(quote)
        }

        override fun onQuotesRangeChanged(month: Int, year: Int, maxRange: Int) {
            view?.setXRange(maxRange)
            loadQuotes(month, year, maxRange)
        }
    }

    private val onResult: (quotes: List<Quote>) -> Unit = {
        view?.initChart(it)
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
        commonPreferencesHelper = CommonPreferencesHelper(view.activity.baseContext)
    }

    private fun loadQuotes(month : Int, year : Int, maxRange: Int) {
        commonPreferencesHelper?.userToken?.let { token ->
            val quotesRequest = quotesConverter.convertDataToQuotesRequest(month, year, maxRange, token)
            chartUseCase?.loadQuotes(quotesRequest, onResult, onError)
        }
    }

    override fun dropView() {
        quotesManager.removeQuoteListener(quotesListener)
        super.dropView()
    }
}