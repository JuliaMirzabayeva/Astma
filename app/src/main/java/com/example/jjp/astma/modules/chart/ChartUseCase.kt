package com.example.jjp.astma.modules.chart

import com.example.jjp.astma.api.request.QuotesRequest
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.models.listeners.ModelLoadingListener
import com.example.jjp.astma.models.quotes.QuotesRepository

class ChartUseCase(private val quotesRepository: QuotesRepository) {
    fun loadQuotes(quotesRequest: QuotesRequest,
                   onResult: (quotes: List<Quote>) -> Unit,
                   onError: (error: String?) -> Unit) {

        quotesRepository.loadQuotes(quotesRequest, object : ModelLoadingListener<List<Quote>> {
            override fun onModelLoaded(model: List<Quote>) {
                onResult(model)
            }

            override fun onModelError(error: String?) {
                onError(error)
            }

            override fun onModelFailure(error: Throwable?) {
                onError(null)
            }
        })
    }
}