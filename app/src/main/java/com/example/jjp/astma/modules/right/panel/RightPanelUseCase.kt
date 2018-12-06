package com.example.jjp.astma.modules.right.panel

import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.models.listeners.ModelLoadingListener
import com.example.jjp.astma.models.quotes.QuotesRepository

class RightPanelUseCase(private val quotesRepository: QuotesRepository) {
    fun addQuote(addQuoteRequest: QuoteRequest,
                 onResult: (quote: Quote) -> Unit,
                 onFailure: ((Throwable?) -> Unit)? = null) {

        quotesRepository.addQuote(addQuoteRequest, object : ModelLoadingListener<Quote> {
            override fun onModelLoaded(model: Quote) {
                onResult(model)
            }

            override fun onModelFailure(error: Throwable?) {
                onFailure?.invoke(error)
            }
        })
    }
}