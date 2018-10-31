package com.example.jjp.astma.modules.top.panel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.main.MainActivity
import kotlinx.android.synthetic.main.fragment_top_panel.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusFragment
import com.twinkle94.monthyearpicker.picker.YearMonthPickerDialog
import java.text.SimpleDateFormat
import java.util.*


@RequiresPresenter(TopPanelFragmentPresenter::class)
class TopPanelFragment : NucleusFragment<TopPanelFragmentPresenter>() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_top_panel, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chartDateLabel.setOnClickListener { _ -> openMonthYearPicker() }
        menuButton.setOnClickListener { _ -> (activity as MainActivity).inflateProfile() }
    }

    fun setDefaultPickerDate() {
        val calendar = Calendar.getInstance()
        setPickerText(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH))
    }

    fun setDefaultQuotesRange(){
        val calendar = Calendar.getInstance()
        changeQuotesRange(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH))
    }

    private fun openMonthYearPicker() {
        YearMonthPickerDialog(activity, YearMonthPickerDialog.OnDateSetListener { year, month ->
            setPickerText(year, month)
            changeQuotesRange(year, month)
        }).show()
    }

    private fun changeQuotesRange(year: Int, month: Int){
        val daysInMonth = getCalendar(year, month).getActualMaximum(Calendar.DAY_OF_MONTH)
        presenter?.changeQuotesRange(daysInMonth)
    }

    private fun setPickerText(year: Int, month: Int) {
        val calendar = getCalendar(year, month)
        val simpleDateFormat = SimpleDateFormat("LLLL yyyy", Locale("ru"))
        chartDateLabel.text = simpleDateFormat.format(calendar.time)
    }

    private fun getCalendar(year: Int, month: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        return calendar
    }
}