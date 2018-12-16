package com.example.jjp.astma.models

import com.example.jjp.astma.data.Quote
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class QuotesManager
@Inject constructor() {
    private var quoteListeners = HashSet<QuoteListener>()

    interface QuoteListener {
        fun onQuoteAdded(quote: Quote)
        fun onQuotesRangeChanged(month: Int, year: Int, maxRange : Int)
    }

    fun addQuoteListener(listener: QuoteListener) {
        quoteListeners.add(listener)
    }

    fun removeQuoteListener(listener: QuoteListener) {
        quoteListeners.remove(listener)
    }

    fun addQuote(quote: Quote) {
        quoteListeners.forEach { it.onQuoteAdded(quote) }
    }

    fun changeQuotesRange(month: Int, year : Int, maxRange: Int){
        quoteListeners.forEach { it.onQuotesRangeChanged(month, year, maxRange) }
    }
}