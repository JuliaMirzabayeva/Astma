package com.example.jjp.astma.modules.chart

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import kotlinx.android.synthetic.main.fragment_chart.*
import nucleus.view.NucleusFragment
import java.util.ArrayList

class ChartFragment : NucleusFragment<ChartFragmentPresenter>() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChart()
    }

    private fun initChart() {
       // val entries = ArrayList<Entry>()
//        entries.add(Entry(0F, 4F))
//        entries.add(Entry(1F, 1F))
//        entries.add(Entry(2F, 2F))
//        entries.add(Entry(3F, 4F))

        val dataSet = LineDataSet(ArrayList<Entry>(), "")



        dataSet.color = ContextCompat.getColor(activity.baseContext, R.color.colorChartLine)
        dataSet.valueTextColor = ContextCompat.getColor(activity.baseContext, R.color.colorLightGrey)

        dataSet.setDrawCircles(false)
        dataSet.setDrawValues(false)

        val xAxis = chart.xAxis // Controlling X axis

        xAxis.position = XAxis.XAxisPosition.BOTTOM // Set the xAxis position to bottom. Default is top

        val months = arrayOf("Jan", "Feb", "Mar", "Apr") //Customizing x axis value

        val formatter = IAxisValueFormatter { value, axis -> months[value.toInt()] }
        xAxis.granularity = 1f // minimum axis-step (interval) is 1
        xAxis.valueFormatter = formatter

        xAxis.textColor = ContextCompat.getColor(activity.baseContext, R.color.colorLightGrey)

        val yAxisRight = chart.axisRight // Controlling right side of y axis
        yAxisRight.isEnabled = false

        val yAxisLeft = chart.axisLeft   // Controlling left side of y axis
        yAxisLeft.granularity = 1f

        yAxisLeft.textColor = xAxis.textColor

//        val data = LineData(dataSet) // Setting Data
//         chart.data = data
//         chart.animateX(2500)


        chart.clear();


        chart.invalidate()  //refresh
    }
}