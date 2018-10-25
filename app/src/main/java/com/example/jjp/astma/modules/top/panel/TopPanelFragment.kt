package com.example.jjp.astma.modules.top.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import kotlinx.android.synthetic.main.fragment_top_panel.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusFragment
import com.twinkle94.monthyearpicker.picker.YearMonthPickerDialog
import java.text.SimpleDateFormat
import java.util.*


@RequiresPresenter(TopPanelFragmentPresenter::class)
class TopPanelFragment : NucleusFragment<TopPanelFragmentPresenter>() {
    private var picker: YearMonthPickerDialog? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_top_panel, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDefaultPickerDate()
        chartDateLabel.setOnClickListener { _ -> openMonthYearPicker() }
    }

    private fun setDefaultPickerDate() {
        val calendar = Calendar.getInstance()
        setPickerText(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH))
    }

    private fun openMonthYearPicker() {
        picker = YearMonthPickerDialog(activity, YearMonthPickerDialog.OnDateSetListener { year, month ->
            setPickerText(year, month)
        })
        picker?.show()
    }

    private fun setPickerText(year: Int, month: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        val simpleDateFormat = SimpleDateFormat("LLLL yyyy", Locale("ru"))
        chartDateLabel.text = simpleDateFormat.format(calendar.time)
    }
}