package com.example.jjp.astma.modules.top.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import com.rackspira.kristiawan.rackmonthpicker.RackMonthPicker
import kotlinx.android.synthetic.main.fragment_top_panel.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusFragment
import java.util.*

@RequiresPresenter(TopPanelFragmentPresenter::class)
class TopPanelFragment : NucleusFragment<TopPanelFragmentPresenter>() {
    private var picker: RackMonthPicker? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_top_panel, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chartDateLabel.setOnClickListener { _ -> openMonthYearPicker() }
    }

    private fun openMonthYearPicker() {
        picker = RackMonthPicker(activity)
                .setLocale(Locale.getDefault())
                .setPositiveButton { month, startDate, endDate, year, monthLabel ->

                }
                .setNegativeButton { _ -> closePicker() }
        picker?.show()
    }

    private fun closePicker() {
        picker?.dismiss()
    }
}