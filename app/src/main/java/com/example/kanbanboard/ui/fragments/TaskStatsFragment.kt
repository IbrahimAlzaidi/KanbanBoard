package com.example.kanbanboard.ui.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.kanbanboard.R
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.databinding.FragmentTaskBinding
import com.github.aachartmodel.aainfographics.aachartcreator.*


class TaskStatsFragment:BaseFragment<FragmentTaskBinding>() {

    val listStatus = arrayListOf(
        "Bar" ,
        "Columnrange" ,
        "Line",
        "Scatter" ,
    )
    val listStatus2 = arrayListOf(
        "Done",
        "In Progress",
        "Block"
    )

    lateinit var db : DbHelper
    override val LOG_TAG: String = "TaskStats Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentTaskBinding
        get() = FragmentTaskBinding::inflate

    override fun setup() {
        db=DbHelper(requireActivity().applicationContext)
        db.filterTaskByStatsChart("Done")
    }

    override fun addCallBack() {
        chart1DataSet(AAChartType.Bar)
        chart2DataSet(AAChartType.Bar)
        dataSpinner()
    }
    private fun dataSpinner(){
        val spinnerAdapter =
            context?.let { ArrayAdapter(it,R.layout.support_simple_spinner_dropdown_item,listStatus) }
        val spinnerAdapter2 =
            context?.let { ArrayAdapter(it,R.layout.support_simple_spinner_dropdown_item,listStatus2) }
        binding?.firstSpinner?.apply {
            adapter = spinnerAdapter
            onClickSpinner()
        }
        binding?.secondSpinner?.apply {
            adapter = spinnerAdapter2
            onClickSpinner()
        }
    }

    private fun onClickSpinner() {

        binding?.firstSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, poisition: Int, p3: Long) {
                when(listStatus[poisition]){
                    listStatus[0] -> {chart1DataSet(AAChartType.Bar)}//yes
                    listStatus[1] -> {chart1DataSet(AAChartType.Columnrange)}//yes
                    listStatus[2] -> {chart1DataSet(AAChartType.Line)}
                    listStatus[3] -> {chart1DataSet(AAChartType.Scatter)}//yes
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding?.secondSpinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                when (listStatus2[position]){
                    listStatus2[0] -> {
                        db.getAllTasksDataSpinner(listStatus2[0])
                        Log.i("MAIN_ACTIVITY","${db.filterTaskByStatsChart("Done")}")
                    }
                    listStatus2[1] -> {}
                    listStatus2[2] -> {}
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun chart1DataSet(typeOfChart : AAChartType){
        val aaChartView = binding?.paiChart
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(typeOfChart)
            .backgroundColor("#FFFFFFFF")
            .dataLabelsEnabled(false)//show number of array on the items
            .legendEnabled(true)//show the filter point
            .polar(true)//make any chart circular
            .title("Status")//display title in the chart header
            .zoomType(AAChartZoomType.XY)//like is name
            .tooltipEnabled(true)//when user click in any point in chart is shown square having details for this points
            .xAxisGridLineWidth(1f)//the circular grid line in the chart
            .xAxisLabelsEnabled(false)//display the xAxis text "Values of #Categories"
            .yAxisLabelsEnabled(false)//display the yAxis text "Values"
            .categories(arrayOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Done")
                    .color("#689F38")
                    .enableMouseTracking(true)
                    .data(db.filterTaskByStatsChart("Done").toTypedArray()),
                AASeriesElement()
                    .name("In Progress")
                    .color("#FFD10F")
                    .enableMouseTracking(true)
                    .data(db.filterTaskByStatsChart("In Progress").toTypedArray()),
                AASeriesElement()
                    .name("Block")
                    .color("#c62828")
                    .data(db.filterTaskByStatsChart("Block").toTypedArray())
                    .enableMouseTracking(true),
            )
            )
            .animationDuration(3000)
            .animationType(AAChartAnimationType.Bounce)
        aaChartView?.aa_drawChartWithChartModel(aaChartModel)
        aaChartView?.aa_updateChartWithOptions(aaChartModel,true)

    }
    private fun chart2DataSet(typeOfChart : AAChartType){
        val aaChartView = binding?.paiChartCard2
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(typeOfChart)
            .backgroundColor("#FFFFFFFF")
            .dataLabelsEnabled(false)//show number of array on the items
            .legendEnabled(true)//show the filter point
            .polar(true)//make any chart circular
            .title("Status")//display title in the chart header
            .zoomType(AAChartZoomType.XY)//like is name
            .tooltipEnabled(true)//when user click in any point in chart is shown square having details for this points
            .xAxisGridLineWidth(1f)//the circular grid line in the chart
            .xAxisLabelsEnabled(true)//display the xAxis text "Values of #Categories"
            .yAxisLabelsEnabled(false)//display the yAxis text "Values"
            .categories(arrayOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Done")
                    .color("#689F38")
                    .enableMouseTracking(true)
                    .data(arrayOf(4,2,1,4,5,6,9,7)),
                AASeriesElement()
                    .name("In Progress")
                    .color("#FFD10F")
                    .enableMouseTracking(true)
                    .data(arrayOf(4,1,3,4,2,1,4,1)),
                AASeriesElement()
                    .name("Block")
                    .color("#c62828")
                    .data(arrayOf(1,0,0,3,2,1,1,0))
                    .enableMouseTracking(true),
            )
            )
            .animationDuration(3000)
            .animationType(AAChartAnimationType.Bounce)
        aaChartView?.aa_drawChartWithChartModel(aaChartModel)
        aaChartView?.aa_updateChartWithOptions(aaChartModel,true)

    }

}
