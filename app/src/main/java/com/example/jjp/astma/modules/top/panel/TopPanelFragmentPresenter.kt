package com.example.jjp.astma.modules.top.panel

import android.os.Bundle
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.models.QuotesManager
import nucleus.presenter.Presenter
import javax.inject.Inject

class TopPanelFragmentPresenter : Presenter<TopPanelFragment>() {
    @Inject lateinit var quotesManager: QuotesManager

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
    }

    override fun onTakeView(view: TopPanelFragment?) {
        super.onTakeView(view)
        view?.setDefaultPickerDate()
        view?.setDefaultQuotesRange()
    }

    fun changeQuotesRange(maxRange : Int){
        quotesManager.changeQuotesRange(maxRange)
    }
}