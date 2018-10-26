package com.example.jjp.astma.modules.chart

import android.os.Bundle
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.models.QuotesManager
import nucleus.presenter.Presenter
import javax.inject.Inject

class ChartFragmentPresenter : Presenter<ChartFragment>() {
    @Inject
    lateinit var quotesManager: QuotesManager

    private val quotesListener = object : QuotesManager.QuoteListener {
        override fun onQuoteAdded(quote: Quote) {
            view?.addQuote(quote)
        }

        override fun onQuotesRangeChanged(maxRange: Int) {
            view?.setXRange(maxRange)
        }
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
    }

    override fun onTakeView(view: ChartFragment?) {
        super.onTakeView(view)
        view?.initChart()
        quotesManager.addQuoteListener(quotesListener)
    }

    override fun dropView() {
        quotesManager.removeQuoteListener(quotesListener)
        super.dropView()
    }
}