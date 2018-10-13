package com.example.jjp.astma.modules.main

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog
import java.util.Calendar


class DatePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity!!, dateSetListener, year, month, day)
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
        (activity as MainActivity).setSelectedDate(day, month, year)
    }

    companion object {
        const val DATE_PICKER_TAG = "date_picker"
    }
}