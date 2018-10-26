package com.example.jjp.astma.modules.chart

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import com.example.jjp.astma.data.Quote
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_chart.*
import nucleus.factory.RequiresPresenter
import nucleus.view.NucleusFragment
import java.util.ArrayList

@RequiresPresenter(ChartFragmentPresenter::class)
class ChartFragment : NucleusFragment<ChartFragmentPresenter>() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_chart, container, false)
    }

    fun initChart() {
        val entries = ArrayList<Entry>()
        entries.add(Entry(1F, 1F))

        val dataSet = LineDataSet(entries, "")

        dataSet.color = ContextCompat.getColor(activity.baseContext, R.color.colorChartLine)
        dataSet.valueTextColor = ContextCompat.getColor(activity.baseContext, R.color.colorLightGrey)

        dataSet.setDrawCircles(false)
        dataSet.setDrawValues(false)

        val xAxis = chart.xAxis

        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.textColor = ContextCompat.getColor(activity.baseContext, R.color.colorLightGrey)

        val yAxisRight = chart.axisRight
        yAxisRight.isEnabled = false

        val yAxisLeft = chart.axisLeft
        yAxisLeft.granularity = 1f

        yAxisLeft.textColor = xAxis.textColor

        val data = LineData(dataSet)
        chart.data = data
        chart.animateX(2500)

        chart.invalidate()
    }

    fun addQuote(quote: Quote) {
        chart.data.addEntry(Entry(quote.date.day.toFloat(), quote.value.toFloat()), 0)
        chart.notifyDataSetChanged()
        chart.invalidate()
    }

    fun setXRange(maxX: Int) {
        chart.setVisibleXRange(1F, maxX.toFloat())
        chart.invalidate()
    }
}