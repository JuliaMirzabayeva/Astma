package com.example.jjp.astma.modules.chart

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjp.astma.R
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.modules.main.MainActivity
import com.github.mikephil.charting.components.LimitLine
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

    fun initChart(quotes: List<Quote>) {
        val entries = ArrayList<Entry>()

        quotes.forEach { quote ->
            val entry = Entry(presenter.getDay(quote).toFloat(), quote.value.toFloat())
            entries.add(entry)
        }

        val dataSet = LineDataSet(entries, "")

        dataSet.color = ContextCompat.getColor(activity.baseContext, R.color.colorChartLine)
        dataSet.valueTextColor = ContextCompat.getColor(activity.baseContext, R.color.colorLightGrey)

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
        chart.animateX(1000)

        chart.invalidate()
    }

    fun addQuotes(quotes: List<Quote>) {
        if (chart.data == null || chart.data.dataSets.isEmpty()) {
            presenter.initChart(quotes)
        } else {
            val entries = ArrayList<Entry>()
            quotes.forEach { quote ->
                val entry = Entry(presenter.getDay(quote).toFloat(), quote.value.toFloat())
                entries.add(entry)
            }
            entries.forEach {
                chart.data.addEntry(it, 0)
            }
        }
        chart.notifyDataSetChanged()
        chart.invalidate()
    }

    fun addQuote(quote: Quote) {
        if (chart.data == null || chart.data.dataSets.isEmpty()) {
            presenter.initChart(listOf(quote))
        } else {
            val entry = Entry(presenter.getDay(quote).toFloat(), quote.value.toFloat())
            chart.data.addEntry(entry, 0)
        }
        chart.notifyDataSetChanged()
        chart.invalidate()
    }

    fun clearChart() {
        chart.data?.clearValues()
        chart.invalidate()
    }

    fun setXRange(maxX: Int) {
        chart.data?.clearValues()
        chart.xAxis.axisMinimum = 1F
        chart.xAxis.axisMaximum = maxX.toFloat()
        chart.invalidate()
    }

    fun addLimitLine(value : Double, color : Int){
        val line = LimitLine(value.toFloat())
        line.lineColor = ContextCompat.getColor(activity.baseContext, color)

        val leftAxis = chart.axisLeft
        leftAxis.addLimitLine(line)
    }

    fun showError(error: String? = null) {
        (activity as MainActivity).showError(error ?: getString(R.string.network_error))
    }
}