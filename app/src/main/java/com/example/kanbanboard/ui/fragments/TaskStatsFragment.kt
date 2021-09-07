package com.example.kanbanboard.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.kanbanboard.R
import com.example.kanbanboard.databinding.FragmentTaskBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartAnimationType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AADataLabels


class TaskStatsFragment:BaseFragment<FragmentTaskBinding>() {

    val list = arrayListOf(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0)
    val list1 = arrayListOf(1.1,2.2,3.3,4.4,5.5,6.6,7.7,8.8)
    val list2 = arrayListOf(10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0)
    val list3 = arrayListOf(15.3,25.4,35.5,45.6,55.7,65.8,75.9,85.2)
    val listStatus = arrayListOf(
        "Area" ,
        "Arearange" ,
        "Areaspline",
        "Areasplinerange" ,
        "Bar" ,
        "Bubble",
        "Boxplot" ,
        "Columnrange" ,
        "Column",
        "Errorbar" ,
        "Funnel" ,
        "Gauge",
        "Line" ,
        "Pyramid" ,
        "Polygon",
        "Pie" ,
        "Spline" ,
        "Scatter",
        "Waterfall" ,
    )


    override val LOG_TAG: String = "TaskStats Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentTaskBinding
        get() = FragmentTaskBinding::inflate

    override fun setup() {

    }

    override fun addCallBack() {
        chartDataSet(list,AAChartType.Pie)
        dataSpinner()

    }
    private fun dataSpinner(){
        val spinnerAdapter =
            context?.let { ArrayAdapter(it,R.layout.support_simple_spinner_dropdown_item,listStatus) }
        binding?.firstSpinner?.apply {
            adapter = spinnerAdapter
            onClickSpinner()
        }}

    private fun onClickSpinner() {

        binding?.firstSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, poisition: Int, p3: Long) {
                when(listStatus[poisition]){
                    listStatus[0] -> {chartDataSet1(list,AAChartType.Area,list3)}//Polygon*//Boxplot*
                    listStatus[1] -> {chartDataSet1(list1,AAChartType.Arearange,list3)}//Pyramid*//Gauge*
                    listStatus[2] -> {chartDataSet1(list2,AAChartType.Areaspline,list3)}//Spline//Scatter
                    listStatus[3] -> {chartDataSet1(list,AAChartType.Areasplinerange,list3)}//Waterfall*//Funnel*
                    listStatus[4] -> {chartDataSet1(list1,AAChartType.Bar,list3)}//Pie//Bubble
                    listStatus[5] -> {chartDataSet1(list,AAChartType.Bubble,list3)}//Error*
                    listStatus[6] -> {chartDataSet1(list1,AAChartType.Boxplot,list3)}//Arearange//Bar
                    listStatus[7] -> {chartDataSet1(list2,AAChartType.Columnrange,list3)}//Area//line
                    listStatus[8] -> {chartDataSet1(list,AAChartType.Column,list3)}//Polygon*//Boxplot*
                    listStatus[9] -> {chartDataSet1(list1,AAChartType.Errorbar,list3)}//Pyramid*//Gauge*
                    listStatus[10] -> {chartDataSet1(list2,AAChartType.Funnel,list3)}//Spline//Scatter
                    listStatus[11] -> {chartDataSet1(list,AAChartType.Gauge,list3)}//Waterfall*//Funnel*
                    listStatus[12] -> {chartDataSet1(list1,AAChartType.Line,list3)}//Pie//Bubble
                    listStatus[13] -> {chartDataSet1(list,AAChartType.Pyramid,list3)}//Error*
                    listStatus[14] -> {chartDataSet1(list1,AAChartType.Polygon,list3)}//Arearange//Bar
                    listStatus[15] -> {chartDataSet1(list2,AAChartType.Pie,list3)}//Area//line
                    listStatus[16] -> {chartDataSet1(list1,AAChartType.Spline,list3)}//Arearange//Bar
                    listStatus[17] -> {chartDataSet1(list2,AAChartType.Scatter,list3)}//Area//line
                    listStatus[18] -> {chartDataSet1(list,AAChartType.Waterfall,list3)}//Polygon*//Boxplot*
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun chartDataSet(data: MutableList<Double>,typeOfChart : AAChartType){
        val aaChartView = binding?.paiChart
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(typeOfChart)
            .backgroundColor("#FFFFFFFF")
            .dataLabelsEnabled(true)
            .yAxisTitle("")
            .yAxisLabelsEnabled(false)
            .legendEnabled(true)
            .polar(true)
            .categories(arrayOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Done")
                    .color("#F4FAFD")
                    .enableMouseTracking(true)
                    .data(data.toTypedArray()),
                AASeriesElement()
                    .color("#FFD10F")
                    .name("In Progress")
                    .data(arrayOf(7.0, 6.9, 9.5, 14.5, 18.2)),
                AASeriesElement()
                    .color("#c62828")
                    .name("Block")
                    .data(arrayOf(0.2, 0.8, 5.7, 11.3, 17.0,)),
            )
            )
            .animationDuration(3000)
        aaChartView?.aa_drawChartWithChartModel(aaChartModel)
        aaChartView?.aa_updateChartWithOptions(aaChartModel,true)

    }
    private fun chartDataSet1(data: MutableList<Double>,typeOfChart : AAChartType,data1: MutableList<Double>){
        val aaChartView = binding?.paiChart
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(typeOfChart)
            .backgroundColor("#F4FAFD")
            .dataLabelsEnabled(true)
            .yAxisTitle("")
            .yAxisLabelsEnabled(false)
            .legendEnabled(true)
            .polar(true)
            .categories(arrayOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Done")
                    .color("#689F38")
                    .enableMouseTracking(true)
                    .data(data.toTypedArray(),)
                    .data(data1.toTypedArray()),
                AASeriesElement()
                    .color("#FFD10F")
                    .name("In Progress")
                    .data(arrayOf(7.0, 6.9, 9.5, 14.5, 18.2)),
                AASeriesElement()
                    .color("#c62828")
                    .name("Block")
                    .data(arrayOf(0.2, 0.8, 5.7, 11.3, 17.0,))
                    .dataLabels(AADataLabels())
                    .enableMouseTracking(true)
                    .innerSize(15)
                ,
            )
            )
            .animationDuration(3000)
            .animationType(AAChartAnimationType.Bounce)
        aaChartView?.aa_drawChartWithChartModel(aaChartModel)
        aaChartView?.aa_updateChartWithOptions(aaChartModel,true)

    }

}
