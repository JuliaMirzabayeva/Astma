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

        val license: String = "<LicenseContract>" +
                "<Customer>mirzabayevajulia@gmail.com</Customer>" +
                "<OrderId>Trial</OrderId>" +
                "<LicenseCount>1</LicenseCount>" +
                "<IsTrialLicense>true</IsTrialLicense>" +
                "<SupportExpires>11/13/2018 00:00:00</SupportExpires>" +
                "<ProductCode>SC-ANDROID-2D-ENTERPRISE-SRC</ProductCode>" +
                "<KeyCode>c37cb767a0a114e88e33b790cbdeead323f5d5ad3216c77d07c78e29af657cb745ee882bf5f4940869d849d7f57002e572a1d165aa8a504f0e2cd49e3ab2ed04d99992b81cc30f0d4a11e8ca4ecf14dea536416c2a2a08f05b25379eee40e188d92e9f357652af717617a7aab21898ece1fd8098ecdf448f01ede7b4dae7424ceaf1b9a819eea1c1c690d7880be6fbfffc3c88d359cff71730f33059fd4353c93cbeb99596266e697f6578efe0c746233073f4ad</KeyCode>" +
                "</LicenseContract>"

        SciChartSurface.setRuntimeLicenseKey(license)

        // Create a SciChartSurface
        val surface = SciChartSurface(this)

        // Get a layout declared in "activity_main.xml" by id
        val chartLayout: LinearLayout = findViewById(R.id.chartContainer)

        // Add the SciChartSurface to the layout
        chartLayout.addView(surface)

        // Initialize the SciChartBuilder
        SciChartBuilder.init(this)

        // Obtain the SciChartBuilder instance
        val sciChartBuilder = SciChartBuilder.instance()

        // Create a numeric X axis
        val xAxis: IAxis = sciChartBuilder.newNumericAxis()
                .withAxisTitle("X Axis Title")
                .withVisibleRange(-5.0, 15.0)
                .build()

        // Create a numeric Y axis
        val yAxis: IAxis = sciChartBuilder.newNumericAxis()
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
        val chartModifiers = sciChartBuilder.newModifierGroup()
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