package com.example.jjp.astma.models.quotes

import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.api.response.QuoteResponse
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.data.QuoteDate
import java.util.*

class QuoteConverter {
    private fun convertQuoteDateToDate(quoteDate: QuoteDate): Date {
        return GregorianCalendar(quoteDate.year, quoteDate.month, quoteDate.day).time
    }

    private fun convertDateToQuoteDate(date: Date): QuoteDate {
        val calendar = GregorianCalendar.getInstance()
        calendar.time = date
        return QuoteDate(calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR))
    }

    fun convertQuoteToQuoteRequest(quote: Quote, token: String): QuoteRequest {
        return QuoteRequest(quote.value,
                convertQuoteDateToDate(quote.date),
                quote.isMorning,
                token)
    }

    fun convertQuotesResponseToQuoteList(quotesResponse: List<QuoteResponse>): List<Quote> {
        return quotesResponse.map { convertQuoteResponseToQuote(it) }
    }

    fun convertQuoteResponseToQuote(quoteResponse: QuoteResponse): Quote {
        return Quote(quoteResponse.value,
                convertDateToQuoteDate(quoteResponse.date),
                quoteResponse.isMorning)
    }
}