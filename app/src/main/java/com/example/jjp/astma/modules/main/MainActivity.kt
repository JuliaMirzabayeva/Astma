package com.example.jjp.astma.modules.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.right.panel.RightPanelFragment
import java.util.*


class MainActivity : AppCompatActivity() {

    val dateChangeListeners = ArrayList<DateChangeListener>()

    interface DateChangeListener{
        fun onDateChanged(day: Int, month: Int, year: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflateRightPanel()
    }

    private fun inflateRightPanel(){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.rightPanelContainer, RightPanelFragment())
        fragmentTransaction.commit()
    }

    fun showDatePicker(){
        val datePickerFragment = DatePickerFragment()
        datePickerFragment.show( supportFragmentManager, DatePickerFragment.DATE_PICKER_TAG)
    }

    fun setSelectedDate(day: Int, month: Int, year: Int){
        dateChangeListeners.forEach { it -> it.onDateChanged(day, month, year) }
    }

    fun addDateListener(listener: DateChangeListener){
        dateChangeListeners.add(listener)
    }

    fun removeDateListener(listener: DateChangeListener){
        dateChangeListeners.remove(listener)
    }
}