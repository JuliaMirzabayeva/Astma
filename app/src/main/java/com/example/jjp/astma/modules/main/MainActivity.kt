package com.example.jjp.astma.modules.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.example.jjp.astma.R
import com.example.jjp.astma.modules.right.panel.RightPanelFragment
import com.scichart.charting.modifiers.ModifierGroup
import com.scichart.charting.visuals.SciChartSurface
import com.scichart.charting.visuals.annotations.HorizontalAnchorPoint
import com.scichart.charting.visuals.annotations.TextAnnotation
import com.scichart.charting.visuals.annotations.VerticalAnchorPoint
import com.scichart.charting.visuals.axes.IAxis
import com.scichart.drawing.utility.ColorUtil
import com.scichart.extensions.builders.SciChartBuilder;
import java.util.*


class MainActivity : AppCompatActivity() {

    private val dateChangeListeners = ArrayList<DateChangeListener>()

    interface DateChangeListener{
        fun onDateChanged(day: Int, month: Int, year: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflateRightPanel()

        initChart()
    }

    private fun inflateRightPanel(){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.rightPanelContainer, RightPanelFragment())
        fragmentTransaction.commit()
    }

    fun showDatePicker(day: Int, month: Int, year: Int){
        val datePickerFragment = DatePickerFragment.createNewInstance(day, month, year)
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

    private fun initChart(){
        // Create a SciChartSurface
        val surface = SciChartSurface(this)

        // Get a layout declared in "activity_main.xml" by id
        val chartLayout : LinearLayout = findViewById(R.id.chartContainer)

        // Add the SciChartSurface to the layout
        chartLayout.addView(surface)

        // Initialize the SciChartBuilder
        SciChartBuilder.init(this)

        // Obtain the SciChartBuilder instance
        val sciChartBuilder = SciChartBuilder.instance()

        // Create a numeric X axis
        val xAxis : IAxis = sciChartBuilder.newNumericAxis()
                .withAxisTitle("X Axis Title")
                .withVisibleRange(-5.0, 15.0)
                .build()

        // Create a numeric Y axis
        val yAxis : IAxis = sciChartBuilder.newNumericAxis()
                .withAxisTitle("Y Axis Title").withVisibleRange(0.0, 100.0).build()

        // Create a TextAnnotation and specify the inscription and position for it
        val textAnnotation = sciChartBuilder.newTextAnnotation()
                .withX1(5.0)
                .withY1(55.0)
                .withText("Hello World!")
                .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
                .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
                .withFontStyle(20F, ColorUtil.White)
                .build()

        // Create interactivity modifiers
        val  chartModifiers = sciChartBuilder.newModifierGroup()
                .withPinchZoomModifier().withReceiveHandledEvents(true).build()
                .withZoomPanModifier().withReceiveHandledEvents(true).build()
                .build()

        // Add the Y axis to the YAxes collection of the surface
        Collections.addAll(surface.yAxes, yAxis)

        // Add the X axis to the XAxes collection of the surface
        Collections.addAll(surface.xAxes, xAxis)

        // Add the annotation to the Annotations collection of the surface
        Collections.addAll(surface.annotations, textAnnotation)

        // Add the interactions to the ChartModifiers collection of the surface
        Collections.addAll(surface.chartModifiers, chartModifiers)

    }
}