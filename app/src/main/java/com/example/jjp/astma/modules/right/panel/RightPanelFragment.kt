package com.example.jjp.astma.modules.right.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.data.QuoteDate
import com.example.jjp.astma.modules.main.MainActivity
import kotlinx.android.synthetic.main.fragment_right_panel.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusFragment
import java.text.SimpleDateFormat
import java.util.*

@RequiresPresenter(RightPanelFragmentPresenter::class)
class RightPanelFragment : NucleusFragment<RightPanelFragmentPresenter>() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_right_panel, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDefaultDate()
        setTimeSelected(true)

        morningButton.setOnClickListener { _ ->
            setTimeSelected(true)
        }
        eveningButton.setOnClickListener { _ ->
            setTimeSelected(false)
        }

        dateLabel.setOnClickListener { _ ->
            val calendar = getCalendar()
            (activity as MainActivity).showDatePicker(calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.YEAR))
        }

        addQuoteButton.setOnClickListener { _ ->
            val value = valueLabel.text.toString().toIntOrNull()
            if (value != null) {
                val calendar = getCalendar()
                val date = QuoteDate(calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR))
                presenter?.addQuote(Quote(value, date, morningButton.isSelected))
            } else {
                (activity as MainActivity).showError(activity.baseContext.getString(R.string.exhale_error))
            }
        }
    }

    private fun getCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = getSimpleDayFormat().parse(dateLabel.text.toString())
        return calendar
    }

    private fun setDefaultDate() {
        setDate(Calendar.getInstance().time)
    }

    private fun setTimeSelected(isMorning: Boolean) {
        morningButton.isSelected = isMorning
        eveningButton.isSelected = !isMorning
    }

    fun setDate(date: Date) {
        dateLabel.text = getSimpleDayFormat().format(date)
    }

    private fun getSimpleDayFormat(): SimpleDateFormat {
        return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    }

    fun showError(error: String? = null) {
        (activity as MainActivity).showError(error ?: getString(R.string.network_error))
    }
}