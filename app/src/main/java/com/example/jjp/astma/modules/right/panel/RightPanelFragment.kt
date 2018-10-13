package com.example.jjp.astma.modules.right.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
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
            val calendar = Calendar.getInstance()
            calendar.time = getSimpleDayFormat().parse(dateLabel.text.toString())
            (activity as MainActivity).showDatePicker(calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.YEAR))
        }
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
}