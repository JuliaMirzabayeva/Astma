package com.example.jjp.astma.modules.main.pickers

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog
import com.example.jjp.astma.modules.main.MainActivity
import java.util.Calendar


class DatePicker : DialogFragment() {
    var day: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    var month: Int = Calendar.getInstance().get(Calendar.MONTH)
    var year: Int = Calendar.getInstance().get(Calendar.YEAR)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(activity!!, dateSetListener, year, month, day)
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
        (activity as MainActivity).setSelectedDate(day, month, year)
    }

    companion object {
        const val DATE_PICKER_TAG = "date_picker"

        fun createNewInstance(day: Int, month: Int, year: Int) : DatePicker {
            val datePickerFragment = DatePicker()
            datePickerFragment.day  = day
            datePickerFragment.month = month
            datePickerFragment.year = year
            return datePickerFragment
        }
    }
}