package com.example.jjp.astma.models

import android.text.format.DateFormat
import com.example.jjp.astma.data.Quote
import java.util.*
import kotlin.collections.HashMap

class QuotesManager {
    var quotes = HashMap<Pair<Int, Int>, MutableList<Quote>>() // Pair<Int, Int> = Pair<year, month>
    private var quoteListeners = HashSet<QuoteListener>()

    interface QuoteListener {
        fun onQuoteAdded(quote: Quote)
        fun onQuoteEdited(quote: Quote)
        fun onQuotesRangeChanged(month: Int, year: Int, maxRange: Int)
    }

    fun addQuoteListener(listener: QuoteListener) {
        quoteListeners.add(listener)
    }

    fun removeQuoteListener(listener: QuoteListener) {
        quoteListeners.remove(listener)
    }

    fun addQuote(quote: Quote) {
        val date = Pair(getYear(quote), getMonth(quote))
        if (quotes[date] != null) {
            quotes[date]?.add(quote)
            quotes[date]?.sortBy { it.date }
        } else {
            quotes[date] = mutableListOf(quote)
        }
        quoteListeners.forEach { it.onQuoteAdded(quote) }
    }

    fun editQuote(quote: Quote) {
        val date = Pair(getYear(quote), getMonth(quote))
        quotes[date]?.let { list ->
            list.find { it.id == quote.id }?.value = quote.value
            quoteListeners.forEach { it.onQuoteEdited(quote) }
        }
    }

    fun changeQuotesRange(month: Int, year: Int, maxRange: Int) {
        quoteListeners.forEach { it.onQuotesRangeChanged(month, year, maxRange) }
    }

    fun setQuotes(list: List<Quote>) {
        if (list.isNotEmpty()) {
            val quote = list[0]
            val date = Pair(getYear(quote), getMonth(quote))
            quotes[date] = list.toMutableList()
        }
    }

    fun getQuoteIdByDate(quote: Quote): Int? {
        val date = Pair(getYear(quote), getMonth(quote))
        return quotes[date]?.let { list -> list.find { isSameDate(it, quote) } }?.id
    }

    fun isNewQuote(quote: Quote): Boolean {
        val date = Pair(getYear(quote), getMonth(quote))
        quotes[date]?.let { list ->
            list.forEach {
                if (isSameDate(it, quote)) {
                    return false
                }
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