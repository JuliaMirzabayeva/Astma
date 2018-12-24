package com.example.jjp.astma.models.quotes

import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.api.request.QuotesRequest
import com.example.jjp.astma.api.response.QuoteResponse
import com.example.jjp.astma.data.Quote
import java.util.*

class QuoteConverter {
    fun convertQuoteToQuoteRequest(quote: Quote, token: String): QuoteRequest {
        return QuoteRequest(quote.value,
                quote.date,
                quote.isMorning,
                token)
    }

    fun convertDataToQuotesRequest(month: Int, year: Int, daysInMonth: Int, token: String): QuotesRequest {
        return QuotesRequest(getGregorianCalendar(year, month, 1),
                getGregorianCalendar(year, month, daysInMonth),
                token)
    }

    fun convertQuotesResponseToQuoteList(quotesResponse: List<QuoteResponse>): List<Quote> {
        return quotesResponse.map { convertQuoteResponseToQuote(it) }
    }

    fun convertQuoteResponseToQuote(quoteResponse: QuoteResponse): Quote {
        return Quote(quoteResponse.id,
                quoteResponse.value,
                quoteResponse.date,
                quoteResponse.isMorning)
    }

    private fun getGregorianCalendar(year: Int, month: Int, day: Int) = GregorianCalendar(year, month, day).time
}