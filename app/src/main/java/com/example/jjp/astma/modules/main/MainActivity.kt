package com.example.jjp.astma.modules.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.right.panel.RightPanelFragment
import java.util.*
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.components.XAxis
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet


class MainActivity : AppCompatActivity() {

    private val dateChangeListeners = ArrayList<DateChangeListener>()

    interface DateChangeListener {
        fun onDateChanged(day: Int, month: Int, year: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflateRightPanel()

        initChart()
    }

    private fun inflateRightPanel() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.rightPanelContainer, RightPanelFragment())
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

    private fun initChart() {

        val chart: LineChart = findViewById(R.id.chart)

        val entries = ArrayList<Entry>()
        entries.add(Entry(0F, 4F))
        entries.add(Entry(1F, 1F))
        entries.add(Entry(2F, 2F))
        entries.add(Entry(3F, 4F))

        val dataSet = LineDataSet(entries, "Customized values")
        dataSet.color = ContextCompat.getColor(this, R.color.colorPrimary)
        dataSet.valueTextColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)

        //****
        // Controlling X axis
        val xAxis = chart.getXAxis()
        // Set the xAxis position to bottom. Default is top
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        //Customizing x axis value
        val months = arrayOf("Jan", "Feb", "Mar", "Apr")

        val formatter = object : IAxisValueFormatter {
            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                return months[value.toInt()]
            }
        }
        xAxis.setGranularity(1f) // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter)

        //***
        // Controlling right side of y axis
        val yAxisRight = chart.getAxisRight()
        yAxisRight.setEnabled(false)

        //***
        // Controlling left side of y axis
        val yAxisLeft = chart.getAxisLeft()
        yAxisLeft.setGranularity(1f)

        // Setting Data
        val data = LineData(dataSet)
        chart.setData(data)
        chart.animateX(2500)
        //refresh
        chart.invalidate()
    }
}