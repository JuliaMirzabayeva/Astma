package com.example.jjp.astma.modules.main

import android.os.Bundle
import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.chart.ChartFragment
import com.example.jjp.astma.modules.right.panel.RightPanelFragment
import android.support.design.widget.Snackbar
import com.example.jjp.astma.modules.top.panel.TopPanelFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val dateChangeListeners = ArrayList<DateChangeListener>()

    interface DateChangeListener {
        fun onDateChanged(day: Int, month: Int, year: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflateChart()
        inflateRightPanel()
        inflateTopPanel()
    }

    private fun inflateRightPanel() {
        inflateFragment(R.id.rightPanelContainer, RightPanelFragment())
    }

    private fun inflateTopPanel() {
        inflateFragment(R.id.topPanelContainer, TopPanelFragment())
    }

    private fun inflateChart() {
        inflateFragment(R.id.chartContainer, ChartFragment())
    }

    private fun inflateFragment(container: Int, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(container, fragment)
        fragmentTransaction.commit()
    }

    fun showDatePicker(day: Int, month: Int, year: Int) {
        val datePickerFragment = DatePickerFragment.createNewInstance(day, month, year)
        datePickerFragment.show(supportFragmentManager, DatePickerFragment.DATE_PICKER_TAG)
    }

    fun setSelectedDate(day: Int, month: Int, year: Int) {
        dateChangeListeners.forEach { it -> it.onDateChanged(day, month, year) }
    }

    fun addDateListener(listener: DateChangeListener) {
        dateChangeListeners.add(listener)
    }

    fun removeDateListener(listener: DateChangeListener) {
        dateChangeListeners.remove(listener)
    }

    fun showError(error : String){
        Snackbar.make(
                rootLayout,
                error,
                Snackbar.LENGTH_SHORT
        ).show()
    }
}