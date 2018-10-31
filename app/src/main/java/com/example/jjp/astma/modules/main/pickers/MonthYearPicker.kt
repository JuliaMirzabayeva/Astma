package com.example.jjp.astma.modules.main.pickers

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.DatePicker
import java.util.*


class MonthYearPicker : DialogFragment(), DatePickerDialog.OnDateSetListener {
    var day: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    var month: Int = Calendar.getInstance().get(Calendar.MONTH)
    var year: Int = Calendar.getInstance().get(Calendar.YEAR)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        return object : DatePickerDialog(activity!!, AlertDialog.THEME_HOLO_LIGHT, this, year, month, day) {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                val day = context.resources.getIdentifier("android:id/day", null, null)
                if (day != 0) {
                    val dayPicker: View? = findViewById(day)
                    dayPicker?.visibility = View.GONE
                }
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {

    }

    companion object {
        const val MONTH_YEAR_PICKER_TAG = "month_year_picker"

        fun createNewInstance(month: Int, year: Int) : MonthYearPicker {
            val monthYearPicker = MonthYearPicker()
            monthYearPicker.month = month
            monthYearPicker.year = year
            return monthYearPicker
        }
    }
}