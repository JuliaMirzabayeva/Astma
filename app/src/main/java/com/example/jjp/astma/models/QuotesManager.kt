package com.example.jjp.astma.models

import android.text.format.DateFormat
import com.example.jjp.astma.data.Quote
import java.util.*

class QuotesManager {
    var quotes = ArrayList<Quote>()
    private var quoteListeners = HashSet<QuoteListener>()

    interface QuoteListener {
        fun onQuoteAdded(quote: Quote)
        fun onQuotesRangeChanged(month: Int, year: Int, maxRange: Int)
    }

    fun addQuoteListener(listener: QuoteListener) {
        quoteListeners.add(listener)
    }

    fun removeQuoteListener(listener: QuoteListener) {
        quoteListeners.remove(listener)
    }

    fun addQuote(quote: Quote) {
        quotes.add(quote)
        quoteListeners.forEach { it.onQuoteAdded(quote) }
    }

    fun changeQuotesRange(month: Int, year: Int, maxRange: Int) {
        quoteListeners.forEach { it.onQuotesRangeChanged(month, year, maxRange) }
    }

    fun setQuotes(list: List<Quote>) {
        quotes.clear()
        quotes.addAll(list)
    }

    fun getQuoteIdByDate(quote: Quote): Int? {
        return quotes.find { it -> isSameDate(it, quote) }?.id
    }

    fun isNewQuote(quote: Quote): Boolean {
        quotes.forEach {
            if (isSameDate(it, quote)) {
                return false
            }
        }
        return true
    }

    private fun isSameDate(quote1: Quote, quote2: Quote): Boolean {
        return (getDay(quote1) == getDay(quote2)
                && getMonth(quote1) == getMonth(quote2)
                && getYear(quote1) == getYear(quote2)
                && quote1.isMorning == quote2.isMorning)
    }

    fun getDay(quote: Quote): Int {
        return DateFormat.format("dd", quote.date).toString().toInt()
    }

    fun getMonth(quote: Quote): Int {
        return DateFormat.format("MM", quote.date).toString().toInt()
    }

    fun getYear(quote: Quote): Int {
        return DateFormat.format("yyyy", quote.date).toString().toInt()
    }
}